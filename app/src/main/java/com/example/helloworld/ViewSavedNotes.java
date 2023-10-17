package com.example.helloworld;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.Adapters.DatabaseHelper;
import com.example.helloworld.Adapters.NoteAdapter;
import com.example.helloworld.Adapters.NotesAdapter;
import com.example.helloworld.Models.Note;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class ViewSavedNotes extends AppCompatActivity {

    private ListView notesListView;
    private NotesAdapter notesAdapter; // Assuming you have a NotesAdapter.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notes);

        notesListView = findViewById(R.id.notesList);
        notesAdapter = new NotesAdapter(this);

// Fetch data using your method and create a Cursor
        Cursor cursor = notesAdapter.getAllNotesCursor();

// Define the columns that you want to display
        String[] fromColumns = {
                DatabaseHelper.COLUMN_NAME,
                DatabaseHelper.COLUMN_DATE,
                DatabaseHelper.COLUMN_SHIFT,
        };

// Define the view IDs in your list item layout where the data should be displayed
        int[] toViews = {
                R.id.txt1,
                R.id.txt2,
                R.id.txt3,
        };

// Create a SimpleCursorAdapter to bind the data to the ListView
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(
                this,
                R.layout.my_list_item, // Define your custom list item layout
                cursor,
                fromColumns,
                toViews,
                0
        );

        notesListView.setAdapter(cursorAdapter);
    }
}

