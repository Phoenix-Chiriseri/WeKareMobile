package com.example.helloworld;

import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import com.example.helloworld.Adapters.AvailableJobsAdapter;
import com.example.helloworld.Adapters.DatabaseHelper;
import com.example.helloworld.Adapters.NoteAdapter;
import com.example.helloworld.Adapters.NotesAdapter;
import com.example.helloworld.Models.Note;

import java.util.ArrayList;
import java.util.List;

public class ViewSavedNotes extends AppCompatActivity {
    private NotesAdapter notesAdapter;
    private SimpleCursorAdapter adapter;
    private ListView listView;
    RecyclerView recyclerNotes;
    List<Note> noteList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notes);
        recyclerNotes = (RecyclerView) findViewById(R.id.recyclerNotes);
        notesAdapter = new NotesAdapter(ViewSavedNotes.this);
        noteList = new ArrayList<Note>();
        fetchNotes();
    }

    private void fetchNotes() {
        Cursor cursor = notesAdapter.getAllNotesCursor();

        while (cursor.moveToNext()){

            String name = cursor.getString(1);
            String date = cursor.getString(2);
            String notesName = cursor.getString(3);
            String shift = cursor.getString(4);

            Note note = new Note();
            note.setNoteName(name);
            note.setDate(date);
            note.setShift(shift);
            note.setJobName(name);
            noteList.add(note);
        }

        // Initialize the adapter and set it to the RecyclerView
        NoteAdapter adapter = new NoteAdapter(ViewSavedNotes.this, noteList);
        recyclerNotes.setAdapter(adapter);
        recyclerNotes.setLayoutManager(new LinearLayoutManager(ViewSavedNotes.this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // You don't need to explicitly close the cursor in this method.
    }
}
