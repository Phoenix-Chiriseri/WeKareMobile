package com.example.helloworld.Adapters;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NotesAdapter extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "job_board";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "notes";
    public static final String COLUMN_NAME = "Name";
    public static final String COLUMN_DATE = "Date";
    public static final String COLUMN_NOTES = "Notes";
    public static final String COLUMN_SHIFT = "Shift";

    private static final String TABLE_CREATE =
            "CREATE TABLE " + TABLE_NAME + " (" +
                    "ID INTEGER PRIMARY KEY AUTOINCREMENT, " + // Corrected AUTOINCREMENT
                    COLUMN_NAME + " TEXT, " +
                    COLUMN_DATE + " TEXT, " +
                    COLUMN_NOTES + " TEXT, " +
                    COLUMN_SHIFT + " TEXT);";

    public NotesAdapter(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(TABLE_CREATE); // Execute the table creation statement

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Add any necessary database schema upgrade logic here
        // For example, you may DROP and recreate the table or make schema changes.
        // Be sure to handle different versions properly.
    }

    public long saveNotes(String name, String jobDate, String jobNotes, String selectedShift) {
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME, name); // Use COLUMN_NAME constant
        values.put(COLUMN_DATE, jobDate); // Use COLUMN_DATE constant
        values.put(COLUMN_NOTES, jobNotes); // Use COLUMN_NOTES constant
        values.put(COLUMN_SHIFT, selectedShift); // Use COLUMN_SHIFT constant
        long rowId = database.insert(TABLE_NAME, null, values);
        return rowId;
    }
}
