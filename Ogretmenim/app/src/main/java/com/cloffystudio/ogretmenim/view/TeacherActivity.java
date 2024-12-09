package com.cloffystudio.ogretmenim.view;

// Gerekli kütüphaneler
import android.content.Intent; // Yeni bir aktiviteye geçiş yapmak için gerekli
import android.os.Bundle; // Aktivite durumu ve verilerini saklamak için kullanılan sınıf
import android.os.Handler; // Zamanlama işlemleri için kullanılan sınıf

import androidx.activity.EdgeToEdge; // Kenardan kenara ekran görünümü için gerekli
import androidx.appcompat.app.AppCompatActivity; // Android aktivitelerinin temel sınıfı
import androidx.core.graphics.Insets; // Sistem kenar boşluklarını yönetmek için kullanılır
import androidx.core.view.ViewCompat; // Görünüm uyumluluğunu artırmak için yardımcı sınıf
import androidx.core.view.WindowInsetsCompat; // Pencere kenar boşluklarını temsil eder
import androidx.recyclerview.widget.LinearLayoutManager; // RecyclerView için düzen yöneticisi
import androidx.recyclerview.widget.LinearSmoothScroller; // RecyclerView öğelerini düzgün kaydırmak için kullanılan sınıf
import androidx.recyclerview.widget.LinearSnapHelper; // RecyclerView öğelerini hizalamak için kullanılan sınıf
import androidx.recyclerview.widget.RecyclerView; // RecyclerView bileşeni
import androidx.recyclerview.widget.SnapHelper; // SnapHelper sınıfı

import com.cloffystudio.ogretmenim.R; // Uygulamanın kaynak dosyalarına erişim sağlar
import com.cloffystudio.ogretmenim.adapter.QuoteAdapter; // Özlü sözleri göstermek için kullanılan adapter
import com.cloffystudio.ogretmenim.adapter.TeacherAdapter; // Öğretmenleri listelemek için kullanılan adapter
import com.cloffystudio.ogretmenim.model.Teacher; // Öğretmen modeli
import com.google.android.material.bottomnavigation.BottomNavigationView; // Alt navigasyon çubuğu bileşeni

import java.util.Arrays; // Dizi işlemleri için gerekli
import java.util.Collections; // Listeyi karıştırmak için kullanılan sınıf
import java.util.List; // Liste veri yapısı

// Öğretmenlerin listelendiği ve özlü sözlerin gösterildiği aktivite
public class TeacherActivity extends AppCompatActivity {

    private RecyclerView teacherRecyclerView; // Öğretmenler için RecyclerView
    private TeacherAdapter teacherAdapter; // Öğretmen adapteri
    private LinearLayoutManager teacherLayoutManager; // Dikey liste düzeni için düzen yöneticisi
    private RecyclerView quoteRecyclerView; // Özlü sözler için RecyclerView
    private QuoteAdapter quoteAdapter; // Özlü sözler adapteri

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Tam ekran görünümü etkinleştirir
        EdgeToEdge.enable(this);

        // Aktivite arayüzünü XML dosyasından yükler
        setContentView(R.layout.activity_teacher);

        // Öğretmenlerin listelendiği RecyclerView'in ayarları
        teacherRecyclerView = findViewById(R.id.teacherRecyclerView);
        teacherLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        teacherRecyclerView.setLayoutManager(teacherLayoutManager);
        teacherAdapter = new TeacherAdapter(this, getRandomTeachers()); // Rastgele sıralanmış öğretmen listesi
        teacherRecyclerView.setAdapter(teacherAdapter);

        // Özlü sözlerin listelendiği yatay RecyclerView'in ayarları
        quoteRecyclerView = findViewById(R.id.quoteRecyclerView);
        quoteRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        quoteAdapter = new QuoteAdapter(this, getQuotes()); // Özlü sözler listesi
        quoteRecyclerView.setAdapter(quoteAdapter);

        // SnapHelper: RecyclerView öğelerini hizalamak için
        SnapHelper snapHelper = new LinearSnapHelper();
        snapHelper.attachToRecyclerView(quoteRecyclerView);

        // Otomatik kaydırma işlemini başlatır
        startAutoScroll();

        // Alt navigasyon çubuğu ayarları
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setSelectedItemId(R.id.nav_teacher); // Varsayılan olarak öğretmen sekmesi seçili
        bottomNavigationView.setOnItemSelectedListener(item -> {
            int itemId = item.getItemId();

            // HomeActivity'ye geçiş
            if (itemId == R.id.nav_home) {
                startActivity(new Intent(this, HomeActivity.class));
                finish(); // Bu aktiviteyi kapatır
                return true;
            }
            // TeacherActivity zaten açık, aynı aktiviteyi tekrar başlatır
            else if (itemId == R.id.nav_teacher) {
                startActivity(new Intent(this, TeacherActivity.class));
                finish(); // Bu aktiviteyi kapatır
                return true;
            }

            return false;
        });
    }

    // Öğretmenler RecyclerView'i için otomatik kaydırma
    private void startAutoScroll() {
        final Handler handler = new Handler(); // Zamanlayıcı işlemler için

        // Kaydırma işlemini belirli aralıklarla gerçekleştiren Runnable
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(TeacherActivity.this) {
                    @Override
                    protected int calculateTimeForScrolling(int dx) {
                        return 1000; // Her kaydırma işlemi için geçen süre (ms)
                    }
                };

                // Şu anki görünümdeki ilk öğeyi al ve sıradaki öğeyi hesapla
                int nextPosition = (teacherLayoutManager.findFirstCompletelyVisibleItemPosition() + 5) % teacherAdapter.getItemCount();
                smoothScroller.setTargetPosition(nextPosition); // Hedef öğe pozisyonu
                teacherLayoutManager.startSmoothScroll(smoothScroller); // Kaydırmayı başlat

                handler.postDelayed(this, 100); // Kaydırma işlemini 100ms sonra tekrar başlat
            }
        };

        handler.postDelayed(runnable, 100); // İlk çalıştırmayı 100ms sonra başlat
    }

    // Öğretmenler listesi: Rastgele sıralanır
    private List<Teacher> getRandomTeachers() {
        List<Teacher> teachers = Arrays.asList(
                new Teacher(1, "Adem Şahin", "Felsefe"),
                new Teacher(2, "Ali Emre Kayan", "Enerji"),
                new Teacher(3, "Arif Demirel", "Bilişim"),
                new Teacher(4, "Armağan Deniz Ulu", "Fizik"),
                new Teacher(5, "Aslı Dalkıran", "Kimya"),
                new Teacher(6, "Ayşe Demirtaş", "İngilizce"),
                new Teacher(7, "Kübra Elri", "Rehberlik"),
                new Teacher(8, "Beyza Nur Süleymanoğlu", "Coğrafya"),
                new Teacher(9, "Bilal Duman", "İngilizce"),
                new Teacher(10, "Buket Mert", "Beden Eğitimi"),
                new Teacher(11, "Cansu Ünal", "Elektrik-Elektronik"),
                new Teacher(12, "Damla Kocaoğlu", "Bilişim"),
                new Teacher(13, "Deniz Ünsal", "Türk Dili ve Edebiyatı"),
                new Teacher(14, "Emine Nur Aluç", "Rehber Öğretmen"),
                new Teacher(15, "Ercan Akgün", "Din Kültürü"),
                new Teacher(16, "Esma Durnaoğlu", "Bilişim"),
                new Teacher(17, "Ezgi Gurbet Işık", "Türk Dili ve Edebiyatı"),
                new Teacher(18, "Fatih Karagöz", "Md. Yrd."),
                new Teacher(19, "Fatma Çelik", "İngilizce"),
                new Teacher(20, "Ferdanur Delininoğlu", "Sosyoloji"),
                new Teacher(21, "Hanife Gözüpek", "Biyoloji"),
                new Teacher(22, "Hatice Çiftçi", "Bilişim"),
                new Teacher(23, "Hatice Çiğdem Bereket", "Enerji"),
                new Teacher(24, "Hayrullah Üzümcü", "Enerji"),
                new Teacher(25, "Hazal Karausta", "Fizik"),
                new Teacher(26, "Serhat Taştan", "Bilişim"),
                new Teacher(27, "Hüseyin Öndoğan", "Beden Eğitimi"),
                new Teacher(28, "İsmail Özdemir", "Bilişim"),
                new Teacher(29, "Kenan Osmanoğlu", "Matematik"),
                new Teacher(30, "Kübra Karakütük", "Matematik"),
                new Teacher(31, "Mehmet Koca", "Tarih"),
                new Teacher(32, "Mehmet Özberber", "Bilişim"),
                new Teacher(33, "Merve Atik", "Enerji"),
                new Teacher(34, "Merve Yazıcı", "Matematik"),
                new Teacher(35, "Mihriban Duygu Polat", "Türk Dili ve Edebiyatı"),
                new Teacher(36, "Muhammed Said Yapıcıoğlu", "Matematik"),
                new Teacher(37, "Murat Kurşun", "Biyoloji"),
                new Teacher(38, "Naciye Nur Topcu", "Biyoloji"),
                new Teacher(39, "Nurcan Gölaç", "Matematik"),
                new Teacher(40, "Oğuzhan Özsu", "Rehberlik"),
                new Teacher(41, "Orhan Bilir", "Felsefe"),
                new Teacher(42, "Ozan Kurt", "Matematik"),
                new Teacher(43, "Seher Tunç", "Din Kültürü"),
                new Teacher(44, "Sibel Yalçın", "Kimya"),
                new Teacher(45, "Sultan Kutluer", "Din Kültürü"),
                new Teacher(46, "Şerife Günaydın", "Bilişim"),
                new Teacher(47, "Şeyma Erdoğan", "Bilişim"),
                new Teacher(48, "Şeyma Güner Oruç", "Türk Dili ve Edebiyatı"),
                new Teacher(49, "Tuğçe Başar", "Türk Dili ve Edebiyatı"),
                new Teacher(50, "Tuğçe Nur Kutlu Çevik", "Coğrafya"),
                new Teacher(51, "Uğur Koçak", "Md. Yrd."),
                new Teacher(52, "Ülker Zeynep Karasu", "Türk Dili ve Edebiyatı"),
                new Teacher(53, "Yağmur Kahraman", "Matematik"),
                new Teacher(54, "Yeliz Aydın", "Rehberlik"),
                new Teacher(55, "Zeynep Balcı", "Tarih"),
                new Teacher(56, "Zeynep Berfin Tunç", "Fizik"),
                new Teacher(57, "Zeynep Uçak", "Rehberlik"),
                new Teacher(58, "Zülfikar Karaçor", "Tarih"),
                new Teacher(59, "Zeynep Çiğdem", "Kimya"),
                new Teacher(60, "Mustafa Şentürk", "Rehber")
        );
        Collections.shuffle(teachers);
        return teachers;
    }


    // Özlü sözlerin listesi
    private List<String> getQuotes() {
        return Arrays.asList(
                "Öğretmenler, geleceğin mimarlarıdır; bilgi ve sevgiyle yoğurdukları her bir öğrenci, dünyayı değiştirme potansiyeline sahiptir.",
                "Bir öğretmenin yüreğine dokunduğu her çocuk, umut dolu bir geleceğe adım atar. Onların bilgeliği ve rehberliği, hayat boyu süren bir ışıktır.",
                "Öğretmenler, bilgi denizinde yol gösteren birer fenerdir; karanlık zamanlarda bile öğrencilere ilham verir ve yön gösterirler.",
                "Öğretmenlerin verdiği emekle nice öğretmenler yetişir. Her biri, dünyayı güzelleştiren birer renk olurlar.",
                "Bir öğretmenin en büyük başarısı, öğrencilerinin kalplerinde bıraktığı izdir. Bu izler, yaşam boyu süren bir yolculuğun temel taşlarıdır.",
                "Öğretmenler, sadece ders anlatmaz; hayata dair değerleri, sevgiyi ve insanlığı öğretirler. Bu yüzden her biri birer kahramandır.",
                "Öğretmenlerin sabrı, bilgeliği ve sevgisi, öğrencilerin hayatında derin izler bırakır. Bu izler, hayat boyu unutulmaz ve daima rehber olur.",
                "Bir öğretmenin verdiği dersler, yalnızca kitap sayfalarında kalmaz; öğrencilerin kalbine ve zihnine işleyerek onların hayatına yön verir.",
                "En zor anlarda bize destek olan öğretmenimiz, teşekkürler!"
        );
    }

}