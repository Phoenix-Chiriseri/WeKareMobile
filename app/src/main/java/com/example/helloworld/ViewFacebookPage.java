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
    Button btnFacebook;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.facebook);

        webView = (WebView) findViewById(R.id.wecareFacebook);
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);


        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                if (url.startsWith("https://www.facebook.com/")) {
                    // Load Facebook URLs in the WebView
                    view.loadUrl(url);
                } else {
                    // Handle other URLs as needed (e.g., open in a browser)
                    // You can customize this part to match your app's behavior.
                }
                return true;
            }
        });

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                // The web page has finished loading, dismiss the progress bar
                progressBar.setVisibility(ProgressBar.INVISIBLE);
            }
        });

        String facebookUrl = "http://www.facebook.com/profile.php?id=61551243421157";
        webView.loadUrl(facebookUrl);
        webView.setBackgroundColor(Color.WHITE);
        webView.getSettings().setJavaScriptEnabled(true);
        //webview will load the url to the job board and people can use the built in browser to navigate to the vie

    }

}
