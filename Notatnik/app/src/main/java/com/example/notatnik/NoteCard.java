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
    long id;
    Button saveButton;
    boolean isNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_card);
        saveButton= findViewById(R.id.save_button);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentBack=new Intent();
                intentBack.putExtra("topic",topicTextView.getText().toString());
                intentBack.putExtra("content",contentTextView.getText().toString());
                intentBack.putExtra("id", id);
                intentBack.putExtra("isNew",isNew);
                setResult(RESULT_OK,intentBack);
                finish();
            }
        });
        Intent intent=getIntent();
        isNew = intent.getBooleanExtra("isNew",false);
        id = intent.getLongExtra("id",0);
        String topic = intent.getStringExtra("topic");
        String content = intent.getStringExtra("content");


        topicTextView = findViewById(R.id.topicTextCardView);
        contentTextView = findViewById(R.id.contentTextCardView);

        topicTextView.setText(topic);
        contentTextView.setText(content);

    }

    @Override
    public void onBackPressed() {

        super.onBackPressed();

    }
}
