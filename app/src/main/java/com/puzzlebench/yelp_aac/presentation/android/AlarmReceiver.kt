package com.puzzlebench.yelp_aac.presentation.android

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.work.Constraints
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        val workerConstraints = Constraints.Builder()
            .setRequiresBatteryNotLow(true).build()
        val updateSeedDataBaseWorker = OneTimeWorkRequestBuilder<UpdateSeedDataBaseWorker>()
            .setConstraints(workerConstraints)
            .build()
        val workManager = WorkManager.getInstance(context)
        workManager.enqueue(updateSeedDataBaseWorker)
    }
}