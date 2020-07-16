package com.puzzlebench.yelp_aac.presentation.android.receiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.puzzlebench.yelp_aac.presentation.android.AlarmManagerHelper

class BootReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "android.intent.action.BOOT_COMPLETED") {
            AlarmManagerHelper(
                context
            ).initSyncAlarm()
        }
    }
}