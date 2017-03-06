package com.example.justin.androidlabs;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

public class WeatherForecast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forecast);

        ProgressBar pBar = (ProgressBar) findViewById(R.id.progressBar);

        pBar.setVisibility(View.VISIBLE);


    }

    class forecastQuery extends AsyncTask<String, Integer, String>{
        String minTemp, maxTemp, currentTemp;
        Bitmap wPic;
        @Override
        protected String doInBackground(String... params) {
            return null;
        }
    }
}
