package com.example.helloworld.Adapters;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;

import com.example.helloworld.Adapters.DatabaseHelper;

public class NotesAdapter {
    private DatabaseHelper dbHelper;

    public NotesAdapter(Context context) {

        dbHelper = new DatabaseHelper(context);
    }

    public long saveNotes(String name, String date, String notes, String shift) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(DatabaseHelper.COLUMN_NAME, name);
        values.put(DatabaseHelper.COLUMN_DATE, date);
        values.put(DatabaseHelper.COLUMN_NOTES, notes);
        values.put(DatabaseHelper.COLUMN_SHIFT, shift);

        long id = db.insert(DatabaseHelper.TABLE_NOTES, null, values);
        db.close();

        return id;
    }

    public Cursor getAllNotesCursor() {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        SQLiteQueryBuilder queryBuilder = new SQLiteQueryBuilder();
        queryBuilder.setTables(DatabaseHelper.TABLE_NOTES);
        String[] projection = {
                DatabaseHelper.COLUMN_ID,
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_DATE,
                DatabaseHelper.COLUMN_NOTES,
                DatabaseHelper.COLUMN_SHIFT
        };
        return queryBuilder.query(db, projection, null, null, null, null, null);
    }
}
