package com.yasination.webviewchecker;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

public class webtoapp extends Activity {

    WebView myWebView;
    ProgressBar progressBar;
    public static String webURL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webtoapp);

        myWebView = findViewById(R.id.myWebView);
        progressBar = findViewById(R.id.progressBar);


        WebSettings webSettings = myWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webSettings.setBuiltInZoomControls(true); // Enable zoom
        webSettings.setDisplayZoomControls(false); // Hide the zoom controls

        myWebView.getSettings().setJavaScriptEnabled(true);

        myWebView.setWebChromeClient(new MyWebChromeClient());
        myWebView.setWebViewClient(new MyWebViewClient());
        // Load a website in the WebView
        myWebView.loadUrl(webURL);
    }

    private class MyWebChromeClient extends WebChromeClient {
        @Override
        public void onProgressChanged(WebView view, int newProgress) {
            progressBar.setProgress(newProgress);
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            progressBar.setVisibility(View.GONE); // or View.INVISIBLE


        }
    }

    @Override
    public void onBackPressed() {
        // Check if the WebView can go back in the history
        if (myWebView.canGoBack()) {
            myWebView.goBack(); // Go back in WebView history
        } else {
            super.onBackPressed(); // If there's no history, perform the default back button action
        }
    }
}
