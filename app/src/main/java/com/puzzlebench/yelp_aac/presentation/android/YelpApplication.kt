package com.puzzlebench.yelp_aac.presentation.android

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.SystemClock
import androidx.core.app.AlarmManagerCompat
import androidx.core.content.ContextCompat
import androidx.preference.PreferenceManager
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import com.puzzlebench.yelp_aac.R
import com.puzzlebench.yelp_aac.presentation.di.ServiceLocator
import com.puzzlebench.yelp_aac.presentation.utils.ThemeManager
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import java.util.concurrent.TimeUnit

private val REQUEST_CODE = 0

class YelpApplication : Application() {
    // TODO this can be improvement this using Dagger2 whit the Scopes
    val businessRepository: BusinessRepository
        get() = ServiceLocator.provideBusinessRepository(this)
    val businessDetailsRepository: BusinessDetailsRepository
        get() = ServiceLocator.provideBusinessDetailsRepository(this)

    override fun onCreate() {
        super.onCreate()
        createChannel()
        initSyncAlarm()
        initTheme()
    }

    private fun initTheme() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        ThemeManager.applyTheme(preferences.getString("preference_key_theme", "")!!)
    }

    private fun initSyncAlarm(){
        createChannel()
        val dailyWorkRequest = OneTimeWorkRequestBuilder<UpdateSeedDataBaseWorker>()
            .setInitialDelay(12, TimeUnit.HOURS)
            .addTag("TAG_OUTPUT_FileClearWorker")
            .build()
        WorkManager.getInstance(applicationContext)
            .enqueue(dailyWorkRequest)

        val alarmManager = this.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val notifyIntent = Intent(this, AlarmReceiver::class.java)
        val triggerTime = SystemClock.elapsedRealtime() + 1_000L * 10
        val notifyPendingIntent = PendingIntent.getBroadcast(
            this,
            REQUEST_CODE,
            notifyIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        AlarmManagerCompat.setExactAndAllowWhileIdle(
            alarmManager,
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            triggerTime,
            notifyPendingIntent
        )
    }

    private fun createChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                this.getString(R.string.syc_notification_channel_id),
                this.getString(R.string.syc_notification_channel_name),
                NotificationManager.IMPORTANCE_LOW
            )
                .apply {
                    setShowBadge(false)
                }

            notificationChannel.enableLights(true)
            notificationChannel.lightColor = Color.RED
            notificationChannel.enableVibration(true)
            notificationChannel.description = getString(R.string.syc_notification_channel_id)

            val notificationManager = this.getSystemService(
                NotificationManager::class.java
            )
            notificationManager.createNotificationChannel(notificationChannel)

        }
    }
}
