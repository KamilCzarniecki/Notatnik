package com.example.notatnik;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{   private ArrayList<noteItem> mNotesList;
    private RecyclerView mRecyclerView;
    private noteAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createNotesList();
        createRecyclerView();
        mAdapter.setOnItemClickListener(new noteAdapter.onItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent= new Intent(MainActivity.this,NoteCard.class);
                intent.putExtra("noteItem",mNotesList.get(position));
                intent.putExtra("position",position);
                startActivityForResult(intent,1);
            }

            @Override
            public void onDeleteClick(int position) {
                deleteNote(position);
            }
        });
    }
    public void createNotesList()
    {
        mNotesList = new ArrayList<>();
        for(int i=0;i<15;i++)
        {
            mNotesList.add(new noteItem(R.drawable.trash, "topic" + i, "content" + i));
        }
        mNotesList.add(new noteItem(R.drawable.trash,"ssssssdddddddddddddddddddddsds","fdddddddddddddddddddfffffffffffffffffffffffffffffffffddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"));
        for(int i=0;i<15;i++)
        {
            mNotesList.add(new noteItem(R.drawable.trash, "topic" + i, "content" + i));
        }

    }
    public void createRecyclerView()
    {
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager= new GridLayoutManager(this,2);
        mAdapter= new noteAdapter(mNotesList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

    }
    public void deleteNote(int position)
    {
     mNotesList.remove(position);
     mAdapter.notifyItemRemoved(position);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1 && resultCode==RESULT_OK){
            noteItem changedItem=data.getParcelableExtra("itemBack");
            int position = data.getIntExtra("position",0);
            mNotesList.set(position,changedItem);
            mAdapter.notifyItemChanged(position);
            Toast.makeText(getApplicationContext(),"Note saved", Toast.LENGTH_SHORT).show();
        }
    }
}
