package com.example.notatnik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private noteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton mFloatingActionButton;
    private SQLiteDatabase mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFloatingActionButton= findViewById(R.id.floatingActionButton);
        NotesDBHelper dbHelper = new NotesDBHelper(this);
        mDatabase= dbHelper.getWritableDatabase();
        createRecyclerView();
        mFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(MainActivity.this,NoteCard.class);
                String topic = "";
                String content= "";
                intent.putExtra("topic",topic);
                intent.putExtra("content",content);
                intent.putExtra("position",0);
                intent.putExtra("isNew", true);
                startActivityForResult(intent,1);
            }
        });
        mAdapter.setOnItemClickListener(new noteAdapter.onItemClickListener() {
            @Override
            public void onItemClick(long id) {
                Intent intent= new Intent(MainActivity.this,NoteCard.class);
                intent.putExtra()
                intent.putExtra("id",id);
                intent.putExtra("isNew",false);
                startActivityForResult(intent,1);
            }

            @Override
            public void onDeleteClick(long id) {
                deleteNote(id);
            }
        });
    }

    public void createRecyclerView()
    {
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager= new GridLayoutManager(this,2);
        mAdapter= new noteAdapter(this,getAllItems());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
    public void deleteNote(long id)
    {
     mDatabase.delete(NotesContract.NoteEntry.TABLE_NAME, NotesContract.NoteEntry._ID + "=" + id,null);
     mAdapter.swapCursor(getAllItems());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK){
            String topic =  data.getStringExtra("topic");
            String content=data.getStringExtra("content");
            long id = data.getLongExtra("position",0);
            boolean isNew = data.getBooleanExtra("isNew",false);
            if(isNew==false) {

                mDatabase.delete(NotesContract.NoteEntry.TABLE_NAME, NotesContract.NoteEntry._ID + "=" + id,null);
            }
            ContentValues cv = new ContentValues();
            cv.put(NotesContract.NoteEntry.COLUMN_TOPIC,topic);
            cv.put(NotesContract.NoteEntry.COLUMN_CONTENT,content);
            mDatabase.insert(NotesContract.NoteEntry.TABLE_NAME,null,cv);
            mAdapter.swapCursor(getAllItems());
            Toast.makeText(getApplicationContext(),"Note saved", Toast.LENGTH_SHORT).show();
        }
    }
    private Cursor getAllItems(){
        return mDatabase.query(
                NotesContract.NoteEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                NotesContract.NoteEntry.COLUMN_TIMESTAMP + " DESC"

        );
    }
}
