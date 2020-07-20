package com.puzzlebench.yelp_aac.presentation.android

import android.app.NotificationManager
import android.content.Context
import androidx.core.content.ContextCompat
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.puzzlebench.yelp_aac.repository.UpdateRepository

class UpdateSeedDataBaseWorker(context: Context, workerParameters: WorkerParameters) :
    CoroutineWorker(context, workerParameters) {
    override suspend fun doWork(): Result {
        val notificationManager = ContextCompat.getSystemService(
            applicationContext,
            NotificationManager::class.java
        ) as NotificationManager

        notificationManager.sendNotification(applicationContext)
        val updateRepository: UpdateRepository =
            (applicationContext as YelpApplication).updateRepository
        updateRepository.updateBusiness()
        return Result.success()
    }
}
