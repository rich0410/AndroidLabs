package com.example.justin.androidlabs;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String activity = "List items activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.i(activity, "onCreate()");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_items);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    protected void onResume(){
        Log.i(activity, "onResume()");
        super.onResume();
    }

    protected void onStart(){
        Log.i(activity, "onStart()");
        super.onStart();
    }

    protected void onPause(){
        Log.i(activity, "onPause()");
        super.onPause();
    }

    protected void onStop(){
        Log.i(activity, "onStop()");
        super.onStop();
    }

    protected void onDestroy(){
        Log.i(activity, "onDestroy()");
        super.onDestroy();
    }



}
