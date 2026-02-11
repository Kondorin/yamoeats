package com.example.yamoeats;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;

public class ManualUsuario extends AppCompatActivity {

    WebView wv;
    String url;

 ;
    //wv.loadData( doc, "text/html", "UTF-8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manual_usuario);
        Bundle datos = this.getIntent().getExtras();
        //url = datos.getString("url");

        wv= (WebView)findViewById(R.id.webViewManual);

        wv.setWebViewClient(new WebViewClient());


        wv.getSettings().setAllowFileAccess(true);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.getSettings().setBuiltInZoomControls(true);
        wv.getSettings().setUseWideViewPort(true);
        wv.setVerticalScrollBarEnabled(false);
        wv.setHorizontalScrollBarEnabled(false);

        wv.loadUrl("https://drive.google.com/open?id=1gdsHPFzxQsTPvsC70dXc6qYwqHuzimnH");
    }
}
