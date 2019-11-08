package com.example.notatnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NoteCard extends AppCompatActivity {
    TextView topicTextView;
    TextView contentTextView;
    int position;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_card);

        Intent intent=getIntent();
        final noteItem noteItem=intent.getParcelableExtra("noteItem");
        position=intent.getIntExtra("position",0);
        String topicString=noteItem.getTextTopic();
        final String contentString=noteItem.getTextContent();

        topicTextView = findViewById(R.id.topicTextCardView);
        contentTextView = findViewById(R.id.contentTextCardView);

        topicTextView.setText(topicString);
        contentTextView.setText(contentString);

    }

    @Override
    public void onBackPressed() {
        Intent intentBack=new Intent();
        noteItem noteItemBack= new noteItem(R.drawable.trash,topicTextView.getText().toString(),contentTextView.getText().toString());
        intentBack.putExtra("itemBack",noteItemBack);
        intentBack.putExtra("position", position);
        setResult(RESULT_OK,intentBack);
        super.onBackPressed();

    }
}
