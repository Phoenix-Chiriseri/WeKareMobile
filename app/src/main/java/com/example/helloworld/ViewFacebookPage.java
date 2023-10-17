package com.example.helloworld;

import android.graphics.Color;
import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ViewFacebookPage extends AppCompatActivity {
    WebView webView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);

        //webview now shwing facebook
        webView = (WebView) findViewById(R.id.wecareFacebook);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        String url = "https://www.facebook.com/profile.php?id=61551243421157";
        webView.loadUrl(url);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);;
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
