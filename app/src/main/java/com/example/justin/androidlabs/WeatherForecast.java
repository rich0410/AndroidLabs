package com.example.justin.androidlabs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Interpolator;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.HttpURLConnection;


public class WeatherForecast extends AppCompatActivity {

    protected static final String activity = "Wheather activity";
    ProgressBar pBar;
    HttpURLConnection conn;
    InputStream in;
    XmlPullParser parser;
    URL url;
    String icon;
    Bitmap weatherPic;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        pBar = (ProgressBar) findViewById(R.id.progressBar);

        pBar.setVisibility(View.INVISIBLE);

        new ForecastQuery().execute();
    }


    class ForecastQuery extends AsyncTask<String, Integer, String> {
        String minTemperature, maxTemperature, currentTemperature;


        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=ottawa," +
                    "ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";

            try {
                url = new URL(urlString);
                conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(10000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setDoInput(true);
                conn.connect();
                in = conn.getInputStream();
                parser = Xml.newPullParser();
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in, null);
                parser.nextTag();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            try {
                while (parser.next() != XmlPullParser.END_TAG) {
                    if (parser.getEventType() != XmlPullParser.START_TAG) {
                        continue;
                    }
                    String name = parser.getName();
                    if (name.equals("temperature")) {
                        minTemperature = parser.getAttributeValue(null, "min");
                        Log.i(activity, minTemperature);
                        this.publishProgress(25);
                        maxTemperature = parser.getAttributeValue(null, "max");
                        Log.i(activity, maxTemperature);
                        this.publishProgress(50);
                        currentTemperature = parser.getAttributeValue(null, "value");
                        Log.i(activity, currentTemperature);
                        this.publishProgress(75);
                    }
                    if (name.equals("wheather")) {
                        icon = parser.getAttributeValue(null, "icon");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... value) {
            pBar.setVisibility(View.VISIBLE);
            pBar.setProgress(value[0]);
            String bitMapURL = "http://openweathermap.org/img/w/" + icon + ".png";

            if (fileExistance(icon + ".png")) {
                Log.i(activity, "Attempting to read " + icon + " from disk");
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(icon);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                weatherPic = BitmapFactory.decodeStream(fis);
            } else {
                Log.i(activity, "Attempting to download " + icon);
                weatherPic = HttpUtils.getImage(bitMapURL);
                try {
                    FileOutputStream outputStream = openFileOutput(icon + ".png", Context.MODE_PRIVATE);
                    weatherPic.compress(Bitmap.CompressFormat.PNG, 80, outputStream);
                    outputStream.flush();
                    outputStream.close();
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        protected void onPostExecute(String result) {
            TextView minText, maxText, currentText;
            ImageView img;

            minText = (TextView) findViewById(R.id.minTemp);
            maxText = (TextView) findViewById(R.id.maxTemp);
            currentText = (TextView) findViewById(R.id.currentTemp);
            img = (ImageView) findViewById(R.id.wImg);

            minText.setText(minTemperature);
            maxText.setText(maxTemperature);
            currentText.setText(currentTemperature);
            img.setImageBitmap(weatherPic);

        }

        public boolean fileExistance(String fname) {
            File file = getBaseContext().getFileStreamPath(fname);
            return file.exists();
        }

    }
}
