package com.cloffystudio.ogretmenim.view;

// Gerekli kütüphaneler
import android.content.Intent; // Yeni bir aktiviteye geçiş yapmak için gerekli
import android.os.Bundle; // Aktivite durumu hakkında bilgi sağlayan sınıf
import android.os.Handler; // Belirli bir süre bekledikten sonra işlem yapmak için kullanılır

import androidx.activity.EdgeToEdge; // Kenardan kenara ekran görünümü desteği
import androidx.appcompat.app.AppCompatActivity; // Android aktivitelerinin temel sınıfı

import com.cloffystudio.ogretmenim.R; // Uygulamanın kaynak dosyalarına erişim sağlar

// Uygulamanın giriş ekranını temsil eden sınıf
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Kenardan kenara tam ekran görünümünü etkinleştirir
        EdgeToEdge.enable(this);

        // Bu aktivitenin arayüzünü "activity_main.xml" dosyasından yükler
        setContentView(R.layout.activity_main);

        // 5 saniye sonra HomeActivity'ye geçiş yapmak için bir işlem planlar
        new Handler().postDelayed(() -> {
            // Intent oluşturulur: MainActivity'den HomeActivity'ye geçiş yapar
            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent); // Yeni aktivite başlatılır

            finish(); // MainActivity kapanır, böylece geri tuşuna basıldığında tekrar bu ekrana dönülmez
        }, 5000); // 5000 milisaniye = 5 saniye bekleme süresi
    }
}