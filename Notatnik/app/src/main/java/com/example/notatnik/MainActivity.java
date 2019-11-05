package com.example.notatnik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
{
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ArrayList<noteItem> notesList = new ArrayList<>();
        for(int i=0;i<15;i++)
        {
            notesList.add(new noteItem(R.drawable.trash, "topic" + i, "content" + i));
        }
        notesList.add(new noteItem(R.drawable.trash,"ssssssdddddddddddddddddddddsds","fdddddddddddddddddddfffffffffffffffffffffffffffffffffddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddddd"));
        for(int i=0;i<15;i++)
        {
            notesList.add(new noteItem(R.drawable.trash, "topic" + i, "content" + i));
        }
        mRecyclerView=findViewById(R.id.recyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager= new GridLayoutManager(this,2);
        mAdapter= new noteAdapter(notesList);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}
