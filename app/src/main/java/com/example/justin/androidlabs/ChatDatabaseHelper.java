package com.example.justin.androidlabs;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Justin on 2017-02-27.
 */

public class ChatDatabaseHelper extends SQLiteOpenHelper {
    protected static String DATABASE_NAME = "MessageTable", KEY_ID = "MessageNumber", KEY_MESSAGE = "Message";
    protected static int VERSION_NUM = 2;

    public ChatDatabaseHelper(Context ctx){
        super(ctx, DATABASE_NAME, null, VERSION_NUM);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.i("ChatDatabaseHelper", "Calling onCreate");
        db.execSQL("CREATE TABLE IF NOT EXISTS " + DATABASE_NAME + " ("+KEY_ID+" integer PRIMARY KEY AUTOINCREMENT, "+KEY_MESSAGE+" String);" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.i("ChatDatabaseHelper", "Calling onUpgrade, oldVersion=" + oldVersion + " newVersion=" + newVersion);
        db.execSQL("DROP TABLE IF EXISTS "+DATABASE_NAME);
        onCreate(db);
    }

    public String getKeyMessage(){
        return KEY_MESSAGE;
    }

    public String getDATABASE_NAME(){
        return DATABASE_NAME;
    }

}
