package com.example.helloworld;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import androidx.appcompat.app.AppCompatActivity;

public class NavigateToJobBoard extends AppCompatActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.navigate);

        mWebView = (WebView) findViewById(R.id.wecareWebView);
        mWebView.setBackgroundColor(Color.WHITE);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.setFocusable(true);
        mWebView.setFocusableInTouchMode(true);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // The web page has finished loading.
                // You can show a dialog here to ask the user if they want to open in an external browser.
            }
        });
        //loadWebPageInWebView();
        openInExternalBrowser();
    }

    public void loadWebPageInWebView() {
        mWebView.loadUrl("https://munanacreatives.co.zw/job-board/");
    }

    public void openInExternalBrowser() {
        String url = "https://munanacreatives.co.zw/job-board/";
        Uri webpage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }
}
