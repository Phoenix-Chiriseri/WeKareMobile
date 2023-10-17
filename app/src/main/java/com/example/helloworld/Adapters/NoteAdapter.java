package com.example.helloworld.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.Models.Job;
import com.example.helloworld.Models.Note;
import com.example.helloworld.NavigateToAvailableJobs;
import com.example.helloworld.R;
import com.example.helloworld.SaveNotes;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private static List<Note> notes;
    Button button;
    private static Context context;
    private Cursor cursor;

    public NoteAdapter(Context context,List<Note> noteList,Cursor cursor) {
        this.context = context;
        this.notes = noteList;
        this.cursor = cursor;
    }


    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        if (cursor.moveToPosition(position)) {
            @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME));
            @SuppressLint("Range") String date = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE));
            @SuppressLint("Range") String notes = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_NOTES));
            @SuppressLint("Range") String shift = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_SHIFT));

            holder.jobNameTextView.setText(name);
            holder.dateTextView.setText(date);
            holder.txtNotes.setText(notes);
            holder.shift.setText(shift);
        }
    }
    @Override
    public int getItemCount() {
        return notes.size();
    }

    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView jobNameTextView;
        TextView dateTextView;
        TextView shift;
        TextView txtNotes;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            jobNameTextView = itemView.findViewById(R.id.textJobName);
            dateTextView = itemView.findViewById(R.id.textDate);
            shift = itemView.findViewById(R.id.textShift);
            txtNotes = itemView.findViewById(R.id.textNotes);

        }
    }
}
