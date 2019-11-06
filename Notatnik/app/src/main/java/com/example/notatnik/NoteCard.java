package com.example.notatnik;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class NoteCard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_card);

        Intent intent=getIntent();
        noteItem noteItem=intent.getParcelableExtra("noteItem");

        String topicString=noteItem.getTextTopic();
        String contentString=noteItem.getTextContent();

        final TextView topicTextView = findViewById(R.id.topicTextCardView);
        final TextView contentTextView = findViewById(R.id.contentTextCardView);

        topicTextView.setText(topicString);
        contentTextView.setText(contentString);

        Button saveButton= findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(),topicTextView.getText().toString()+" "+contentTextView.getText().toString(),Toast.LENGTH_SHORT).show();
            Intent intentBack=new Intent();
            String topicString=topicTextView.getText().toString();
            intentBack.putExtra("topicBack",topicString);
            setResult(RESULT_OK,intentBack);
            finish();
            }
            });
    }
}
