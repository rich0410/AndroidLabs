package com.example.justin.androidlabs;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;

public class ChatWindow extends AppCompatActivity {

    Button sendButton;
    EditText textBox;
    ListView lView;
    ArrayList<String> chatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_window);
        sendButton = (Button) findViewById(R.id.sendButton);
        textBox = (EditText) findViewById(R.id.editText3);
        lView = (ListView) findViewById(R.id.listView);
        chatList = new ArrayList<String>();
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = textBox.getText().toString();
                chatList.add(text);
            }
        });
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
            
        }
    }
}
