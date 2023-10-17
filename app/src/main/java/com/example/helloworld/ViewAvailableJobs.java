package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.helloworld.Adapters.AvailableJobsAdapter;
import com.example.helloworld.Models.Job;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class ViewAvailableJobs extends AppCompatActivity {

    RecyclerView availableJobRecycler;
    List<Job> listOfJobs;
    OkHttpClient client;
    Request request;
    String availableJobsUrl = "https://munanacreatives.co.zw/job-board/availablejobs";
    String bodyString;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_available_jobs);
        client = new OkHttpClient();
        listOfJobs = new ArrayList<Job>();
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        availableJobRecycler = (RecyclerView) findViewById(R.id.recyclerAvailableJobs);
        //fetch the available jobs into the recycler view and set them to the adapter
        showAvailableJobs();
    }

    private void showAvailableJobs() {

        progressBar.setVisibility(View.VISIBLE); // Show the progress bar
        client = new OkHttpClient();
        final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        request = new Request.Builder().url(availableJobsUrl).get().build();
        client.newCall(request).enqueue(new Callback() {

            public Response intercept(Interceptor.Chain chain) throws IOException {
                Response response = chain.proceed(chain.request());
                ResponseBody body = response.body();
                bodyString = body.string();
                MediaType contentType = body.contentType();
                Log.d("Response", bodyString);
                return response.newBuilder().body(ResponseBody.create(contentType, bodyString)).build();
            }

            //fetching json data well
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {

            }

            // transmission failure callback
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {

                //string that contains the json response from the weather api endpoint
                progressBar.setVisibility(View.INVISIBLE); // Show the progress bar
                final String string = response.body().string();
                Log.i("advice", "onResponse: " + string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        //Snackbar.make(v,string,Snackbar.LENGTH_LONG).show();
                        AvailableJobsAdapter adapter = new AvailableJobsAdapter(ViewAvailableJobs.this,listOfJobs); // Initialize the adapter with the list
                        availableJobRecycler.setAdapter(adapter);
                        availableJobRecycler.setLayoutManager(new LinearLayoutManager(ViewAvailableJobs.this));
                        try {
                            JSONArray rootArray = new JSONArray(string);
                            for(int i=0;i<rootArray.length();i++) {

                                JSONObject object = rootArray.getJSONObject(i);
                                Job singleJob = new Job();
                                singleJob.setName(object.getString("job_name"));
                                singleJob.setDate(object.getString("date"));
                                singleJob.setShift(object.getString("shift"));
                                singleJob.setId(object.getString("id"));
                                listOfJobs.add(singleJob);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        });
    }
}
