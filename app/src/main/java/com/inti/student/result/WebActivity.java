package com.inti.student.result;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class WebActivity extends AppCompatActivity {
    private WebView webView;
    android.support.v7.widget.Toolbar tb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        webView = findViewById(R.id.webview);
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl("https://webapp.services.coventry.ac.uk/Apps/Student/0/Login.htm?fbclid=IwAR3zjcIn_p_cno2oH57Zug_XmcfFFhWkPfZxOUzUFq9ohpl5RGVljR91qtQ");

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        tb = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);

        setSupportActionBar(tb);
        tb.setNavigationIcon(getResources().getDrawable(R.drawable.back));
        getSupportActionBar().setTitle("Coventry Website");
        //add back arrow to toolbar
    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //handle arrow click
        if(item.getItemId() == android.R.id.home){
            finish(); //close this activity & return to preview activity
        }
        return super.onOptionsItemSelected(item);
    }
}
