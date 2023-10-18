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
    private static List<Note> noteList;
    Button button;
    private static Context context;

    public NoteAdapter(Context context,List<Note> noteList) {
        this.context = context;
        this.noteList = noteList;

    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item, parent, false);
        return new NoteViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Note note = noteList.get(position);
        holder.jobName.setText("Job Name-"+note.getJobName());
        holder.dateName.setText("Date-"+note.getDate());
        holder.txtNotes.setText("Notes-"+note.getNoteName());
        holder.shift.setText("Shift is"+ " "+ note.getShift());
    }
    @Override
    public int getItemCount() {
        return noteList.size();
    }
    public static class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView jobName;
        TextView dateName;
        TextView shift;
        TextView txtNotes;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            jobName = itemView.findViewById(R.id.textJobName);
            dateName = itemView.findViewById(R.id.textDate);
            shift = itemView.findViewById(R.id.textShift);
            txtNotes = itemView.findViewById(R.id.textNotes);

        }
    }
}
