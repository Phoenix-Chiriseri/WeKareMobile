package com.example.helloworld;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.helloworld.Adapters.AvailableJobsAdapter;
import com.example.helloworld.Models.Job;

import org.jetbrains.annotations.NotNull;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.OkHttp;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class SearchAboutJob extends AppCompatActivity {


    EditText editText;
    Button btnSearch;
    OkHttpClient client;
    Response response;
    Request request;
    String bodyString;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_job);

        editText = (EditText) findViewById(R.id.edtSearch);
        btnSearch = (Button)findViewById(R.id.searchButton);

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchTerm = editText.getText().toString().toLowerCase();
                if (!searchTerm.isEmpty()) {
                    performSearch(searchTerm);
                }
            }
        });
    }

    private void performSearch(String searchTerm) {

        client = new OkHttpClient();
        final MediaType JSON
                = MediaType.get("application/json; charset=utf-8");
        String searchUrl = "https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + searchTerm + "&utf8=&format=json";

        Request request = new Request.Builder()
                .url(searchUrl)
                .get()
                .build();
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

                //string that contains the json response from the weather api endpoind
                final String string = response.body().string();
                Log.i("response", "onResponse: " + string);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        try {

                            JSONObject root = new JSONObject(string);
                            JSONObject query = root.getJSONObject("query");
                            Log.d("query",query.toString());
                            JSONArray array  = query.getJSONArray("Search");
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject obj = array.getJSONObject(i); // Use index 'i' here
                                String title = obj.getString("title");
                                String snippet = obj.getString("snippet");
                                Log.d("details", title + snippet);
                                showInDialog(title,snippet);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }
        });

    }

    private void showInDialog(String title,String snippet) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(SearchAboutJob.this);
        // Set the title and message in the AlertDialog
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder.setMessage(snippet);
        // Add a button to close the dialog
        alertDialogBuilder.setPositiveButton("OK", null);
        // Create and show the AlertDialog
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();

    }
}
