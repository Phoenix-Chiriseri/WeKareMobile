package com.example.helloworld;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.Models.Note;

import java.util.ArrayList;

public class ViewSavedNotes extends AppCompatActivity {

    RecyclerView recyclerView;
    ArrayList<Note> notes;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_notes);
        recyclerView = (RecyclerView) findViewById(R.id.notesRecycler);


    }
}
