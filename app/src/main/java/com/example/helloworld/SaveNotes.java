package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.*;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.helloworld.Adapters.DatabaseHelper;
import com.example.helloworld.Adapters.NoteAdapter;
import com.example.helloworld.Adapters.NotesAdapter;
import com.example.helloworld.Models.Note;
import com.google.android.material.snackbar.Snackbar;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class SaveNotes extends AppCompatActivity {

    EditText jobName,jobDate,jobNotes;
    Button saveNotes;
    NotesAdapter notesAdapter;
    Spinner shiftSpinner;
    String selectedShift;
    Button viewSavedNotes;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.notes);

        jobName = (EditText) findViewById(R.id.jobNameEditText);
        jobDate = (EditText) findViewById(R.id.dateEditText);
        jobNotes = (EditText)findViewById(R.id.noteNameEditText);
        saveNotes = (Button) findViewById(R.id.saveButton);
        shiftSpinner = (Spinner)findViewById(R.id.shiftSpinner);
        viewSavedNotes = (Button)findViewById(R.id.viewSavedNotes);


        viewSavedNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(getApplicationContext(),ViewSavedNotes.class));

            }
        });

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.shift_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        shiftSpinner.setAdapter(adapter);

        // Set a listener for the spinner
        shiftSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                //Toast.makeText(SaveNotes.this, "Selected Shift: " + selectedShift, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Do nothing
            }
        });

        saveNotes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String name = jobName.getText().toString();
                String date = jobDate.getText().toString();
                String notes = jobNotes.getText().toString();
                selectedShift = shiftSpinner.getSelectedItem().toString();
                if (name.equals("") || date.equals("") || notes.equals("") || selectedShift.equals("")) {
                    Snackbar.make(view, "Please Dont Leave Any Empty Fields", Snackbar.LENGTH_SHORT).show();
                } else {
                    // Define a file name for your text file
                    NotesAdapter myAdapter = new NotesAdapter(SaveNotes.this);
                    long saved = myAdapter.saveNotes(name, date, notes, selectedShift);
                    if (saved != -1) {
                        Toast.makeText(getApplicationContext(), "Notes Saved" + saved, Toast.LENGTH_LONG).show();
                    } else {
                        Toast.makeText(getApplicationContext(), "Notes Not Saved", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}
