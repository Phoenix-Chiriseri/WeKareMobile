package com.example.helloworld.Adapters;

import android.content.Context;
import android.content.Intent;
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
import com.example.helloworld.NavigateToAvailableJobs;
import com.example.helloworld.R;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
    private static List<Job> jobList;
    Button button;
    private static Context context;

    public NoteAdapter(Context context,List<Job> jobList) {
        this.context = context;
        this.jobList = jobList;
    }
    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_note_item, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.jobNameTextView.setText(job.getName());
        holder.dateTextView.setText(job.getDate());
        holder.shift.setText("Shift is"+ " "+ job.getShift());
        //holder.txtNavigateToResults.setText(job.getId());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
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
