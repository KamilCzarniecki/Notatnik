package com.example.notatnik;

import android.provider.BaseColumns;

public class NotesContract {

    private NotesContract(){}

    public static final class NoteEntry implements BaseColumns{
        public static final String TABLE_NAME="NotesList";
        public static final String COLUMN_TOPIC="topic";
        public static final String COLUMN_CONTENT="content";
        public static final String COLUMN_TIMESTAMP="timestamp";
    }
}
