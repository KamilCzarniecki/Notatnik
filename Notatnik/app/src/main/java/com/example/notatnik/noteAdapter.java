package com.example.notatnik;

import android.content.Context;
import android.database.Cursor;
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

    private onItemClickListener mListener;
    private Context mContext;
    private Cursor mCursor;
    public interface onItemClickListener
    {
        void onItemClick(long id);
        void onDeleteClick(long id);
    }
    public void  setOnItemClickListener(onItemClickListener listener){
        mListener=listener;
    }

    public static class noteViewHolder extends RecyclerView.ViewHolder
    {
        public ImageView mImageView;
        public TextView mTextView1;
        public TextView mTextView2;

        public noteViewHolder(final View itemView, final onItemClickListener listener){
            super(itemView);
            mImageView=itemView.findViewById(R.id.noteImageView);
            mTextView1=itemView.findViewById(R.id.topicTextView);
            mTextView2=itemView.findViewById(R.id.contentTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        long id =(long) itemView.getTag();
                            listener.onItemClick(id);

                    }
                }
            });
            mImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener !=null){
                        long id =(long) mImageView.getTag();
                        listener.onDeleteClick(id);

                    }
                }
            });
        }
    }
    public noteAdapter(Context context, Cursor cursor)
    {
        mContext=context;
        mCursor=cursor;
    }
    @NonNull
    @Override
    public noteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        noteViewHolder nvh = new noteViewHolder(v,mListener);
        return nvh;
    }

    @Override
    public void onBindViewHolder(@NonNull noteViewHolder holder, int position) {
        if(!mCursor.moveToPosition(position)){
            return;
        }
        String topic = mCursor.getString(mCursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_TOPIC));
        String content = mCursor.getString(mCursor.getColumnIndex(NotesContract.NoteEntry.COLUMN_CONTENT));
        long id = mCursor.getLong(mCursor.getColumnIndex(NotesContract.NoteEntry._ID));
        holder.mImageView.setImageResource(R.drawable.trash);
        holder.mTextView1.setText(topic);
        holder.mTextView2.setText(content);
        holder.mImageView.setTag(id);
        holder.itemView.setTag(id);
    }

    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }
    public void swapCursor(Cursor newCursor){
        if(mCursor!=null){
            mCursor.close();
        }
        mCursor=newCursor;
        if(newCursor!=null){
            notifyDataSetChanged();
        }
    }
}
