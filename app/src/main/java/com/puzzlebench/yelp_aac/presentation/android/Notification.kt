package com.puzzlebench.yelp_aac.presentation.android
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.puzzlebench.yelp_aac.R

// Notification ID.
private val NOTIFICATION_ID = 0

/**
 * Builds and delivers the notification.
 *
 * @param context, activity context.
 */
fun NotificationManager.sendNotification(applicationContext: Context) {

    val contentIntent = Intent(applicationContext, MainActivity::class.java)
    val contentPendingIntent = PendingIntent.getActivity(
        applicationContext,
        NOTIFICATION_ID,
        contentIntent,
        PendingIntent.FLAG_UPDATE_CURRENT
    )

    val builder = NotificationCompat.Builder(
        applicationContext,
        applicationContext.getString(R.string.syc_notification_channel_id)
    )
        .setSmallIcon(R.drawable.ic_settings)
        .setContentTitle(applicationContext
            .getString(R.string.notification_title))
        .setContentText(applicationContext
            .getString(R.string.notification_content))
        .setContentIntent(contentPendingIntent)
        .setAutoCancel(false)
        .setPriority(NotificationCompat.PRIORITY_LOW)
    notify(NOTIFICATION_ID, builder.build())
}

/**
 * Cancels all notifications.
 *
 */
fun NotificationManager.cancelNotifications() {
    cancelAll()
}
