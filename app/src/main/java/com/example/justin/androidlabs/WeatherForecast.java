package com.example.justin.androidlabs;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Xml;
import android.view.View;
import android.widget.ProgressBar;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.HttpURLConnection;

public class WeatherForecast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        ProgressBar pBar = (ProgressBar) findViewById(R.id.progressBar);

        pBar.setVisibility(View.VISIBLE);


    }

    class ForecastQuery extends AsyncTask<String, Integer, String>{
        String  minTemperature, maxTemperature, currentTemperature;
        Bitmap weatherPic;

        @Override
        protected String doInBackground(String... params) {
            String urlString = "http://api.openweathermap.org/data/2.5/weather?q=ottawa," +
                    "ca&APPID=d99666875e0e51521f0040a3d97d0f6a&mode=xml&units=metric";
            URL url = null;
            HttpURLConnection conn = null;
            InputStream in = null;
            XmlPullParser parser = null;

            try
            {
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
            }
            catch (MalformedURLException e){
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e){
                e.printStackTrace();
            }

            try {
                while (parser.next()) {

                }
            }
            catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e){
                e.printStackTrace();
            }

            return conn.getInputStream();
        }
    }
}
