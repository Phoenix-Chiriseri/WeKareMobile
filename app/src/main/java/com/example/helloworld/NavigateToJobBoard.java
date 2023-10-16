package com.example.helloworld;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class NavigateToJobBoard extends AppCompatActivity {

    //set the wevbview to the application
    WebView mWebView;
    ProgressBar progressBar;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigate);
        //the webview will load the page and will set the content to that page
        mWebView = (WebView) findViewById(R.id.wecareWebView);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // The web page has finished loading, dismiss the progress bar
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });

        mWebView.setBackgroundColor(Color.WHITE);
        mWebView.getSettings().setJavaScriptEnabled(true);
        //webview will load the url to the job board and people can use the built in browser to navigate to the view
        mWebView.loadUrl("https://munanacreatives.co.zw/job-board/");
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
