package com.cloffystudio.ogretmenim.view;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.LinearSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SnapHelper;

import com.cloffystudio.ogretmenim.R;
import com.cloffystudio.ogretmenim.adapter.ImageAdapter;
import com.cloffystudio.ogretmenim.receiver.NotificationWorker;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Arrays;
import java.util.Calendar;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import androidx.work.Data;
import androidx.work.ExistingPeriodicWorkPolicy;
import androidx.work.PeriodicWorkRequest;
import androidx.work.WorkManager;

public class HomeActivity extends AppCompatActivity {

    // Öğretmenlere ait rastgele mesajların tutulduğu bir String dizi
    String[] teacherMessages = {
            "Sizin gibi bir öğretmen herkese nasip olmaz!",
            "Bize kattıklarınız için teşekkür ederiz!",
            "Bir öğretmen her zaman ilham kaynağıdır!",
            "Eğitim hayatımızda sizin yeriniz apayrı!",
            "Her gün bizlere yeni şeyler öğretiyorsunuz!",
            "Siz olmasaydınız, bugünkü başarılarımız mümkün olmazdı.",
            "Bir öğretmenin verdiği bilgi paha biçilemez!",
            "Sabırlı ve fedakar öğretmenimiz, size minnettarız.",
            "Siz eğitim hayatımızın rehberisiniz.",
            "Her gün bizim için ne kadar emek verdiğinizi biliyoruz!",
            "En zor anlarda bize destek olan öğretmenimiz, teşekkürler!"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tam ekran görünüm için Edge-to-Edge özelliğini etkinleştirir
        EdgeToEdge.enable(this);

        // Aktivitenin görünümünü XML'den yükler
        setContentView(R.layout.activity_home);

        // İlk RecyclerView kurulumu
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        ImageAdapter adapter = new ImageAdapter(this, Arrays.asList(
                R.drawable.img_00,
                R.drawable.img_01,
                R.drawable.img_02,
                R.drawable.img_03,
                R.drawable.img_04,
                R.drawable.img_06
        ));
        recyclerView.setAdapter(adapter);

        // SnapHelper, RecyclerView öğelerinin hizalanmasını sağlar
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        // Oyun ekranına geçiş için buton ayarı
        ImageButton gameButton = findViewById(R.id.imageButton4);
        gameButton.setOnClickListener(v -> {
            startActivity(new Intent(HomeActivity.this, GameActivity.class)); // GameActivity'yi başlatır
        });

        // Alt navigasyon çubuğu ayarları
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                finish();
                return true;
            } else if (itemId == R.id.nav_teacher) {
                startActivity(new Intent(this, TeacherActivity.class));
                finish();
                return true;
            }

            return false;
        });

        // Bildirim izni kontrolü (Android 13 ve üzeri cihazlar için)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(
                        this, new String[]{Manifest.permission.POST_NOTIFICATIONS}, 101
                );
            }
        }

        // Günlük bildirim planlama
        scheduleDailyNotification();
    }

    // Günlük saat 8:30'da bildirim göndermek için zamanlama
    private void scheduleDailyNotification() {
        // Rastgele bir mesaj seçimi
        String randomMessage = teacherMessages[new Random().nextInt(teacherMessages.length)];

        // Mesajı WorkManager'a göndermek için Data nesnesi oluştur
        Data data = new Data.Builder()
                .putString("message", randomMessage)
                .build();

        // Her gün saat 8:30'da çalışacak şekilde bir PeriodicWorkRequest oluştur
        PeriodicWorkRequest workRequest = new PeriodicWorkRequest.Builder(
                NotificationWorker.class,
                1, TimeUnit.DAYS // 1 günde bir çalışacak
        )
                .setInputData(data)
                .setInitialDelay(calculateInitialDelay(), TimeUnit.MILLISECONDS) // İlk çalıştırma için 8:30'a zamanla
                .build();

        // WorkManager'a iş isteğini planla
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
                "DailyNotificationWork", // İşe özel bir ad veriyoruz
                ExistingPeriodicWorkPolicy.REPLACE, // Var olanı değiştir
                workRequest
        );
    }

    // İlk bildirimin 8:30'da tetiklenmesi için zaman farkını hesaplar
    private long calculateInitialDelay() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 8);  // 8:00
        calendar.set(Calendar.MINUTE, 30);      // 8:30
        calendar.set(Calendar.SECOND, 0);       // 0 saniye

        // Şu anki zamandan 8:30'a kadar olan süreyi hesapla
        if (calendar.getTimeInMillis() <= System.currentTimeMillis()) {
            calendar.add(Calendar.DAY_OF_YEAR, 1); // Eğer saat geçmişse, bir sonraki gün için ayarla
        }

        return calendar.getTimeInMillis() - System.currentTimeMillis();
    }
}
