package com.example.helloworld.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.Models.Job;
import com.example.helloworld.R;

import java.util.List;

public class AvailableJobsAdapter extends RecyclerView.Adapter<AvailableJobsAdapter.JobViewHolder> {
    private List<Job> jobList;

    public AvailableJobsAdapter(List<Job> jobList) {
        this.jobList = jobList;
    }

    @NonNull
    @Override
    public JobViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_item, parent, false);
        return new JobViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull JobViewHolder holder, int position) {
        Job job = jobList.get(position);
        holder.jobNameTextView.setText(job.getName());
        holder.dateTextView.setText(job.getDate());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView jobNameTextView;
        TextView dateTextView;
        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            jobNameTextView = itemView.findViewById(R.id.jobNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
        }
    }
}
