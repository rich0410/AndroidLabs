package com.example.justin.androidlabs;

import android.app.Activity;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.content.Intent;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

public class ListItemsActivity extends AppCompatActivity {

    protected static final String activity = "List items activity";

    ImageButton imgButton;
    Switch s;
    CheckBox cBox;

    final static int REQUEST_IMAGE_CAPTURE = 1;

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

        imgButton = (ImageButton) findViewById(R.id.imagebutton);
        s = (Switch) findViewById(R.id._switch);
        cBox = (CheckBox) findViewById(R.id.checkbox);

        imgButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                takePicture();
            }
        });

        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    CharSequence text = "Switch is On";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
                    toast.show();
                }
                else if (!isChecked){
                    CharSequence text = "Switch is Off";
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(ListItemsActivity.this, text, duration);
                    toast.show();
                }

            }
        });

        cBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(ListItemsActivity.this);
                    builder.setMessage(R.string.dialog_message)
                            .setTitle(R.string.dialog_title)
                            .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    Intent resultIntent = new Intent(  );
                                    resultIntent.putExtra("Response", "My information to share");
                                    setResult(Activity.RESULT_OK, resultIntent);
                                    finish();
                                }
                            })
                            .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    // User cancelled the dialog
                                }
                            })
                            .show();
                }
            }
        });



    }

    protected void takePicture(){
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imgButton.setImageBitmap(imageBitmap);
        }
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
