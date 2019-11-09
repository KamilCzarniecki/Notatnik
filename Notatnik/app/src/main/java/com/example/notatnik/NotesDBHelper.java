package com.example.notatnik;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class NotesDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME="NotesList.db";
    public static final int DATABASE_VERSION = 1;
    public NotesDBHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_NOTESLIST_TABLE= "CREATE TABLE "+
                NotesContract.NoteEntry.TABLE_NAME+ " (" +
                NotesContract.NoteEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
                NotesContract.NoteEntry.COLUMN_TOPIC + " TEXT NOT NULL, " +
                NotesContract.NoteEntry.COLUMN_CONTENT+ " TEXT NOT NULL, " +
                NotesContract.NoteEntry.COLUMN_TIMESTAMP+ " TIMESTAMP DEFAULT CURRENT_TIMESTAMP"+
                ");";
        db.execSQL(SQL_CREATE_NOTESLIST_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS "+NotesContract.NoteEntry.TABLE_NAME );
            onCreate(db);
    }
}
