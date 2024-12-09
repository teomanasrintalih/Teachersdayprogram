package com.cloffystudio.ogretmenim.receiver;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.core.app.NotificationCompat;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.cloffystudio.ogretmenim.R;
import com.cloffystudio.ogretmenim.view.HomeActivity;

public class NotificationWorker extends Worker {

    private static final String CHANNEL_ID = "daily_notification_channel";
    private static final int NOTIFICATION_ID = 1;

    public NotificationWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        // Mesaj verisini al
        String message = getInputData().getString("message");

        // Mesaj varsa, bildirimi göster
        if (message != null && !message.isEmpty()) {
            showNotification(message);
        } else {
            // Varsayılan mesaj
            showNotification("Bugünün mesajı hazır!");
        }

        // İş başarılı olarak tamamlandı
        return Result.success();
    }

    private void showNotification(String message) {
        Context context = getApplicationContext();

        // Bildirim kanalı oluşturma (Android 8.0 ve üzeri için)
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    CHANNEL_ID,
                    "Günlük Bildirimler",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("Günlük motivasyon mesajları");
            if (notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }

        // Bildirime tıklanınca açılacak olan aktivite
        Intent intent = new Intent(context, HomeActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                0,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
        );

        // Bildirim oluşturma
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, CHANNEL_ID)
                .setSmallIcon(R.drawable.ic_launcher_foreground) // Bildirim simgesi
                .setContentTitle("Öğretmenim!") // Bildirim başlığı
                .setContentText(message) // Bildirim içeriği
                .setPriority(NotificationCompat.PRIORITY_HIGH) // Bildirim önceliği
                .setAutoCancel(true) // Tıklanınca bildirimi kaldır
                .setContentIntent(pendingIntent); // Tıklama olayını bağla

        // Bildirimi göster
        if (notificationManager != null) {
            notificationManager.notify(NOTIFICATION_ID, builder.build());
        }
    }
}
