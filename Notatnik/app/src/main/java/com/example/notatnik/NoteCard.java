package com.example.notatnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class NoteCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_card);
        Intent intent=getIntent();
        noteItem noteItem=intent.getParcelableExtra("noteItem");
        String topicString=noteItem.getTextTopic();
        String contentString=noteItem.getTextContent();
        TextView topicTextView= findViewById(R.id.topicTextCardView);
        TextView contentTextView=findViewById(R.id.contentTextCardView);
        topicTextView.setText(topicString);
        contentTextView.setText(contentString);

    }
}
