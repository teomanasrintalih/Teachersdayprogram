package com.cloffystudio.ogretmenim.view;

import android.os.Bundle;
import android.webkit.WebSettings; // WebView'in ayarlarını yönetmek için gerekli sınıf
import android.webkit.WebView; // WebView bileşeni
import android.webkit.WebViewClient; // WebView için tarayıcı davranışı tanımlamak için kullanılan sınıf

import androidx.activity.EdgeToEdge; // Tam ekran (edge-to-edge) görünümler için gerekli sınıf
import androidx.appcompat.app.AppCompatActivity; // Android aktivitelerinin temel sınıfı

import com.cloffystudio.ogretmenim.R; // Uygulamanın kaynaklarına erişmek için gerekli sınıf

public class GameActivity extends AppCompatActivity {

    // WebView tanımlaması: Uygulama içinde bir web sayfası göstermek için kullanılır
    WebView myWeb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tam ekran görünümü etkinleştirir (Edge-to-Edge)
        EdgeToEdge.enable(this);

        // Aktivitenin arayüzünü `activity_game.xml` dosyasından yükler
        setContentView(R.layout.activity_game);

        // WebView bileşenini XML'deki tanımlı `Website` ID'si ile ilişkilendirir
        myWeb = findViewById(R.id.Website);

        // WebView'e bir `WebViewClient` atanır
        // Bu, WebView'in link tıklamalarını dış tarayıcı yerine kendi içinde açmasını sağlar
        myWeb.setWebViewClient(new WebViewClient());

        // WebView'in ayarlarını almak için `WebSettings` kullanılır
        WebSettings webSettings = myWeb.getSettings();

        // WebView'de JavaScript'i etkinleştirir
        // Bazı modern web sayfalarının düzgün çalışması için gereklidir
        webSettings.setJavaScriptEnabled(true);

        // WebView'de DOM Depolamayı etkinleştirir
        // HTML5 özelliklerini desteklemek için bu ayar gerekebilir
        webSettings.setDomStorageEnabled(true);

        // WebView'e belirli bir URL yükler
        // Bu örnekte, "https://webmobiloyun.netlify.app/" adresi yükleniyor
        myWeb.loadUrl("https://webmobiloyun.netlify.app/");
    }
}