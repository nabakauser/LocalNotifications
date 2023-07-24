package com.example.locnotifs

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import android.widget.Toast
import androidx.core.app.NotificationManagerCompat

class NotificationBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent?) {
//        if (intent?.action == "CANCEL_ACTION") {
//            val notification = createCancelNotification(context)
//            displayCancelNotification(notification,context)
//
//        }
    }
}

fun createCancelNotification(context: Context): Notification {
    val cancelIntent = Intent(context, MainActivity::class.java)
    val cancelPendingIntent = PendingIntent.getActivity(context, 200, cancelIntent, PendingIntent.FLAG_IMMUTABLE)
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        val notificationManager = context.getSystemService(NotificationManager::class.java)
        val notificationChannel = NotificationChannel(
            "cancelNotificationChannelId",
            "LocalNotification",
            NotificationManager.IMPORTANCE_HIGH
        ).apply { enableVibration(true) }

        notificationManager?.createNotificationChannel(notificationChannel)
    }
    val builder: Notification.Builder =
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) Notification.Builder(
            context, "cancelNotificationChannelId")
        else Notification.Builder(context)

    return builder
        .setSmallIcon(R.mipmap.ic_launcher_round)
        .setContentTitle("EMERGENCY ALERT CANCELLED")
        .setContentText("You have cancelled the emergency alert, it has been notified to your emergency contacts")
        .setContentIntent(cancelPendingIntent)
        .setAutoCancel(false)
        .build()
}

@SuppressLint("MissingPermission")
fun displayCancelNotification(notification: Notification, context: Context) {
    val notificationManagerCompat = NotificationManagerCompat.from(context)
    notificationManagerCompat.notify(222, notification)
    notificationManagerCompat.cancel(123)
}