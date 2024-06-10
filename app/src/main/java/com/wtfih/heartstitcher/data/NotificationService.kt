package com.wtfih.heartstitcher.data

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import com.wtfih.heartstitcher.R
import kotlin.random.Random

class WaterNotificationService(
    private val context: Context
) {
    private val notificationManager = context.getSystemService(NotificationManager::class.java)

    fun drinkNotification() {
        val notification = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink some water!")
            .setSmallIcon(R.drawable.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun foodNotification() {
        val notification = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Food Reminder")
            .setContentText("Time to eat!")
            .setSmallIcon(R.drawable.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
/*val intent = Intent(context, DestinationActivity::class.java)
intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP

val pendingIntent = PendingIntent.getActivity(
    context,
    0,
    intent,
    PendingIntent.FLAG_UPDATE_CURRENT
)

val notification = NotificationCompat.Builder(context, "water_reminder")
    .setContentTitle("Water Reminder")
    .setContentText("Time to drink some water!")
    .setSmallIcon(R.drawable.water_icon)
    .setPriority(NotificationCompat.PRIORITY_HIGH)
    .setContentIntent(pendingIntent) // Dodaj PendingIntent do powiadomienia
    .setAutoCancel(true)
    .build()*/
    fun A() {
        val notification = NotificationCompat.Builder(context, "water_reminder")
            .setContentTitle("Water Reminder")
            .setContentText("Time to drink some water!")
            .setSmallIcon(R.drawable.ic_launcher_round)
            .setPriority(NotificationCompat.PRIORITY_MIN)
            .setAutoCancel(true)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources,
        resId
    )
}