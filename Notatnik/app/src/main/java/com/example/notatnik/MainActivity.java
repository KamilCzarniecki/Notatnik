package com.example.notatnik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.notatnik.BuildConfig;
import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private noteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    FloatingActionButton mFloatingActionButton;
    private SQLiteDatabase mDatabase;
    private SharedPreferences mOptionsPreferences;
    private SharedPreferences.Editor mOptionsPrefEditor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createOptionsSharedPreferences();
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
                intent.putExtra("isNew", true);
                startActivityForResult(intent,1);
            }
        });
        mAdapter.setOnItemClickListener(new noteAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position, long id) {
                Intent intent= new Intent(MainActivity.this,NoteCard.class);
                Cursor tempCursor=getAllItems();
                tempCursor.moveToPosition(position);
                String topic = tempCursor.getString(tempCursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_TOPIC));
                String content =tempCursor.getString(tempCursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_CONTENT));
                intent.putExtra("id",id);
                intent.putExtra("isNew",false);
                intent.putExtra("topic",topic);
                intent.putExtra("content",content);
                startActivityForResult(intent,1);
            }

            @Override
            public void onDeleteClick(long id) {
                deleteNote(id);
            }
        });
    }

    public void createRecyclerView() {
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager= new GridLayoutManager(this,mOptionsPreferences.getInt("LayoutType",mAdapter.SPAN_COUNT_GRID));
        mAdapter= new noteAdapter(this,getAllItems());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
    public void deleteNote(long id) {
        Toast.makeText(MainActivity.this,String.valueOf(id), Toast.LENGTH_SHORT).show();
        mDatabase.delete(NotesContract.NoteEntry.TABLE_NAME, NotesContract.NoteEntry._ID + "=" + id,null);
        mAdapter.swapCursor(getAllItems());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK){
            String topic =  data.getStringExtra("topic");
            String content=data.getStringExtra("content");
            long id = data.getLongExtra("id",0);
            boolean isNew = data.getBooleanExtra("isNew",false);
            if(isNew==false) {
                mDatabase.delete(NotesContract.NoteEntry.TABLE_NAME, NotesContract.NoteEntry._ID + "=" + id,null);
            }
            ContentValues cv = new ContentValues();
            cv.put(NotesContract.NoteEntry.COLUMN_TOPIC,topic);
            cv.put(NotesContract.NoteEntry.COLUMN_CONTENT,content);
            mDatabase.insert(NotesContract.NoteEntry.TABLE_NAME,null,cv);
            mAdapter.swapCursor(getAllItems());
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu,menu);
        if(mOptionsPreferences.getInt("LayoutType",mAdapter.SPAN_COUNT_GRID)==mAdapter.SPAN_COUNT_GRID) {
            menu.findItem(R.id.grid_layout).setChecked(true);
        }
        else{
            menu.findItem(R.id.list_layout).setChecked(true);
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()){
            case R.id.list_layout:
                mOptionsPrefEditor.putInt("LayoutType",mAdapter.SPAN_COUNT_LIST);
                mOptionsPrefEditor.commit();
                item.setChecked(true);
                switchLayout();
                return true;
            case R.id.grid_layout:
                mOptionsPrefEditor.putInt("LayoutType",mAdapter.SPAN_COUNT_GRID);
                mOptionsPrefEditor.commit();
                item.setChecked(true);
                switchLayout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void switchLayout(){
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,mOptionsPreferences.getInt("LayoutType",mAdapter.SPAN_COUNT_GRID)));
        mAdapter.notifyItemRangeChanged(0,mAdapter.getItemCount());
    }
    public boolean firstRunCheck(){
        SharedPreferences preferences = getSharedPreferences("FirstRunCheck", MODE_PRIVATE);
        if (preferences.getBoolean("FirstLogin", true)) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("FirstLogin", false);
            editor.commit();
            return true;
        }
        else {
            return false;
        }
    }
    public void createOptionsSharedPreferences(){
        mOptionsPreferences = getSharedPreferences("OptionsPreferences",MODE_PRIVATE);
        mOptionsPrefEditor = mOptionsPreferences.edit();
            if(firstRunCheck()){
                mOptionsPrefEditor.putInt("LayoutType",mAdapter.SPAN_COUNT_GRID);
                mOptionsPrefEditor.commit();
            }
    }
}
