package com.example.notatnik;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class noteAdapter extends RecyclerView.Adapter<noteAdapter.noteViewHolder>
{
    private ArrayList mNoteItemList;

    public static class noteViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public noteViewHolder(View itemView){
            super(itemView);
            mImageView=itemView.findViewById(R.id.noteImageView);
            mTextView1=itemView.findViewById(R.id.topicTextView);
            mTextView2=itemView.findViewById(R.id.contentTextView);
        }
    }
    public noteAdapter(ArrayList<noteItem> noteList ){
        mNoteItemList=noteList;
    }
    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        noteViewHolder nvh = new noteViewHolder(v);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull noteViewHolder holder, int position) {
    noteItem currentItem = (noteItem) mNoteItemList.get(position);
    holder.mImageView.setImageResource(currentItem.getImageResource());
    holder.mTextView1.setText(currentItem.getTextTopic());
    holder.mTextView2.setText(currentItem.getTextContent());
    }

    @Override
    public int getItemCount() {
        return mNoteItemList.size();
    }
}
