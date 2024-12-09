package com.cloffystudio.ogretmenim.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import androidx.work.Data;
import androidx.work.OneTimeWorkRequest;
import androidx.work.WorkManager;
import com.cloffystudio.ogretmenim.utils.NotificationHelper;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        // Mesaj verisini al
        String message = intent.getStringExtra("message");


    }
}
