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

public class AvailableJobsAdapter extends RecyclerView.Adapter<AvailableJobsAdapter.JobViewHolder> {
    private static List<Job> jobList;
    Button button;
    private static Context context;

    public AvailableJobsAdapter(Context context,List<Job> jobList) {
        this.context = context;
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
        holder.shift.setText("Shift is"+ " "+ job.getShift());
        //holder.txtNavigateToResults.setText(job.getId());
    }

    @Override
    public int getItemCount() {
        return jobList.size();
    }

    public static class JobViewHolder extends RecyclerView.ViewHolder {
        TextView jobNameTextView;
        TextView dateTextView;
        TextView shift;
        TextView txtNavigateToResults;
        public JobViewHolder(@NonNull View itemView) {
            super(itemView);
            jobNameTextView = itemView.findViewById(R.id.jobNameTextView);
            dateTextView = itemView.findViewById(R.id.dateTextView);
            shift = itemView.findViewById(R.id.txtShift);
            txtNavigateToResults = itemView.findViewById(R.id.txtNavigateToResults);
            txtNavigateToResults.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition(); // Get the position of the clicked item
                    if (position != RecyclerView.NO_POSITION) {
                        Job job = jobList.get(position);
                        String jobId = job.getId(); // Retrieve the job ID
                        Log.d("id",jobId);
                        Intent navigateToAvailableJobs = new Intent(context, NavigateToAvailableJobs.class);
                        Bundle extras = new Bundle();
                        extras.putString("id", jobId);
                        navigateToAvailableJobs.putExtras(extras);
                        context.startActivity(navigateToAvailableJobs);
                    }
                }
            });
        }
    }
}
