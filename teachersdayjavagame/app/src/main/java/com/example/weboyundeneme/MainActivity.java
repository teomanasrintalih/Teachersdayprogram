package com.example.weboyundeneme;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    WebView myWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main.xml layout dosyasını bağladık

        // WebView tanımlama
        myWeb = findViewById(R.id.Myweb);
        myWeb.setWebViewClient(new WebViewClient()); // Linkleri dış tarayıcıda açmamak için

        // WebView ayarları
        WebSettings webSettings = myWeb.getSettings();
        webSettings.setJavaScriptEnabled(true); // JavaScript kullanımı için
        webSettings.setDomStorageEnabled(true); // HTML5 için gerekli olabilir

        // Belirtilen siteyi yükleme
        myWeb.loadUrl("https://webmobiloyun.netlify.app/");
    }
}
