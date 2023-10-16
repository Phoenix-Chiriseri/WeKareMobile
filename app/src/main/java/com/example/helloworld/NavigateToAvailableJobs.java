package com.example.helloworld;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NavigateToAvailableJobs extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigate_jobs);

        Intent intent = new Intent();
        String id = intent.getStringExtra("id");
        Toast.makeText(getApplicationContext(),"Id is"+id,Toast.LENGTH_LONG).show();
    }
}
