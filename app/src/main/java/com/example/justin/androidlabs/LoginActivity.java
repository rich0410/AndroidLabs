package com.example.justin.androidlabs;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.Map;
import java.util.Set;

public class LoginActivity extends AppCompatActivity {

    protected static final String activity = "Login activity";

    SharedPreferences sPref;
    EditText emailText;
    SharedPreferences.Editor writer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(activity, "onCreate");
        setContentView(R.layout.activity_login);
        Button loginButton = (Button) findViewById(R.id.button);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        sPref = getSharedPreferences("DefaultEmail", Context.MODE_PRIVATE);

        emailText = (EditText) findViewById(R.id.editText);

        writer = sPref.edit();

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                writer.putString("DefaultEmail", email);
                writer.commit();
                Intent intent = new Intent(LoginActivity.this, StartActivity.class);
                startActivity(intent);
            }
        });


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
        emailText.setText(sPref.getString("DefaultEmail", "email@domain.com"));
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
