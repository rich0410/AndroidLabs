package com.example.justin.androidlabs;

import android.app.Activity;
import android.content.Intent;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {

    protected static final String activity = "Start activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(activity, "onCreate");
        setContentView(R.layout.activity_start);
        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Context context = getApplicationContext();
                Intent intent = new Intent(context, ListItemsActivity.class);
                startActivityForResult(intent, 5);
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

    protected void onActivityResult(int requestCode, int responseCode, Intent data){
        if(requestCode == 5){
            Log.i(activity, "Returned to StartActivity.onActivityResult");
        }
        if (responseCode == Activity.RESULT_OK){
            String messagePassed = data.getStringExtra("Response");
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this , messagePassed, duration);
            toast.show();
        }
    }

}
