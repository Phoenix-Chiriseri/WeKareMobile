package com.example.helloworld;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.Models.Job;

import java.util.ArrayList;
import java.util.List;

public class ViewAvailableJobs extends AppCompatActivity {


    RecyclerView availableJobRecycler;
    List<Job> listOfJobs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_available_jobs);
         listOfJobs= new ArrayList<Job>();
         availableJobRecycler = (RecyclerView)findViewById(R.id.recyclerAvailableJobs);

    }
}
