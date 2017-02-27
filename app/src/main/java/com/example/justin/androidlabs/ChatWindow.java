package com.example.justin.androidlabs;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    Button sendButton;
    EditText textBox;
    ListView lView;
    ArrayList<String> chatList;
    ChatAdapter messageAdapter;
    ChatDatabaseHelper cDBH;
    SQLiteDatabase dbWrite;
    Cursor c;
    ContentValues cv;
    protected static final String activity = "Chat Activity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        sendButton = (Button) findViewById(R.id.sendButton);
        textBox = (EditText) findViewById(R.id.editText3);
        lView = (ListView) findViewById(R.id.listView);
        chatList = new ArrayList<String>();
        messageAdapter =new ChatAdapter( this );
        cDBH = new ChatDatabaseHelper(this);
        dbWrite= cDBH.getWritableDatabase();
        c = dbWrite.rawQuery("SELECT Message FROM ?", new String[]{"MessageTable"});
        cv = new ContentValues();

        lView.setAdapter (messageAdapter);
        textBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textBox.setText("");
            }
        });
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chatList.add(textBox.getText().toString());
                cv.put(cDBH.getKeyMessage(), textBox.getText().toString());
                dbWrite.insert(cDBH.getDATABASE_NAME(), null, cv);
                messageAdapter.notifyDataSetChanged();
                textBox.setText("");
            }
        });

        while(!c.isAfterLast()) {
            Log.i(activity, "SQL MESSAGE:"+c.getString(c.getColumnIndex(cDBH.KEY_MESSAGE)));
        }

        Log.i(activity, "Cursor’s column count =" + c.getColumnCount());

        for (int x = 0; x<c.getColumnCount(); x++){
            Log.i(activity, ""+c.getColumnName(x));
        }
    }

    private class ChatAdapter extends ArrayAdapter<String>{
        public ChatAdapter(Context context){
            super(context, 0);
        }

        public int getCount(){
            return chatList.size();
        }

        public String getItem(int position){
            return chatList.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent){
            LayoutInflater inflater = ChatWindow.this.getLayoutInflater();
            View result = null ;
            if(position%2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView)result.findViewById(R.id.message_text);
            message.setText(   getItem(position)  );
            return result;


        }
    }

    @Override
    protected void onDestroy(){
        dbWrite.close();
        super.onDestroy();
    }

}
