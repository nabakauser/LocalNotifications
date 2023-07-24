package com.example.locnotifs

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.view.ContentInfoCompat.Flags


fun createNotification(context: Context): Notification {
    val intent = Intent(context, MainActivity::class.java)
    val pendingIntent = PendingIntent.getActivity(context, 100, intent, PendingIntent.FLAG_IMMUTABLE)

    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)

        val notificationChannel = NotificationChannel(
            "notificationChannelId", "LocalNotification", NotificationManager.IMPORTANCE_HIGH
        ).apply {
            enableVibration(true)
        }

        notificationManager?.createNotificationChannel(notificationChannel)
    }
    val builder: Notification.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
            context,
            "notificationChannelId"
        )
        else Notification.Builder(context)

    val actionIntent = Intent(context, MainActivity::class.java)
    actionIntent.putExtra("CANCEL_ACTION",true)
    Log.d("CancelActions","first notif - true")
    val actionPendingIntent = PendingIntent.getActivity(context, 0, actionIntent, PendingIntent.FLAG_IMMUTABLE)

    val action = Notification.Action.Builder(
        R.mipmap.ic_launcher,
        "Cancel",
        actionPendingIntent
    ).build()

    return builder
        .setSmallIcon(R.drawable.ic_alert)
        .setContentTitle("EMERGENCY ALERT SENT!")
        .setContentText("Notified to your emergency contacts")
        .setContentIntent(pendingIntent)
        .setAutoCancel(false)
        .addAction(action)
        .setOngoing(true)
        .build()
}

@SuppressLint("MissingPermission")
fun displayNotification(notification: Notification, context: Context) {
    val notificationManagerCompat = NotificationManagerCompat.from(context)
    notificationManagerCompat.notify(123, notification)
}