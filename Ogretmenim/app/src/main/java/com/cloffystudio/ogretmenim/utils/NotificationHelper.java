package com.cloffystudio.ogretmenim.utils;

import android.app.NotificationChannel; // Bildirim kanalları Android 8.0+ için kullanılır
import android.app.NotificationManager; // Bildirim yönetimi için gerekli sınıf
import android.content.Context; // Uygulama bağlamına erişmek için kullanılan sınıf
import android.os.Build; // Android sürüm kontrolü için kullanılır

import androidx.core.app.NotificationCompat; // Bildirim oluşturmak için gerekli sınıf

import com.cloffystudio.ogretmenim.R; // Kaynak dosyalarına erişim sağlar

// Bildirim gösterimi için yardımcı sınıf
public class NotificationHelper {

    // Bildirim kanalının kimliği ve adı
    private static final String CHANNEL_ID = "teacher_messages_channel"; // Kanalın benzersiz kimliği
    private static final String CHANNEL_NAME = "Teacher Messages"; // Kanalın kullanıcıya görünen adı

    // Bildirim gösterimi için statik yardımcı metod
    public static void showNotification(Context context, String title, String message) {
        // Bildirim yöneticisini (NotificationManager) al
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Eğer cihaz Android 8.0 (Oreo) veya üzeri bir sürümde çalışıyorsa, bir bildirim kanalı oluştur
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            // Kanalın özelliklerini ayarla
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID, // Kanalın kimliği
                    CHANNEL_NAME, // Kanalın adı (Kullanıcıya görünür)
                    NotificationManager.IMPORTANCE_HIGH // Bildirimin öncelik seviyesi
            );
            // Kanalı bildirim yöneticisine ekle
            notificationManager.createNotificationChannel(channel);
        }

        // Bildirim oluşturucu (NotificationCompat.Builder) kullanarak bildirimi oluştur
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.osb_logo) // Bildirim simgesi
                .setContentTitle(title) // Bildirimin başlığı
                .setContentText(message) // Bildirimin metni
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Öncelik seviyesi (Android 7.1 ve altı için)
                .setAutoCancel(true); // Bildirim tıklandığında otomatik olarak kaybolur

        // Bildirimi göster
        notificationManager.notify(1, builder.build()); // notify() ile bildirimi yayınla
    }
}