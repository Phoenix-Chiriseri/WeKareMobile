package com.example.helloworld;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NavigateToAvailableJobs extends AppCompatActivity {

    WebView webView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigate_jobs);

        String id = getIntent().getStringExtra("id");
        webView = (WebView) findViewById(R.id.nav);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // The web page has finished loading, dismiss the progress bar
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });

        webView.setBackgroundColor(Color.WHITE);
        webView.getSettings().setJavaScriptEnabled(true);
        //webview will load the url to the job board and people can use the built in browser to navigate to the view
        webView.loadUrl("https://munanacreatives.co.zw/job-board/viewJob/"+id);
    }
}
