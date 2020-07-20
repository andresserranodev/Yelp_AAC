package com.puzzlebench.yelp_aac.presentation.android

import android.app.Application
import androidx.preference.PreferenceManager
import com.facebook.stetho.Stetho
import com.puzzlebench.yelp_aac.BuildConfig
import com.puzzlebench.yelp_aac.presentation.di.ServiceLocator
import com.puzzlebench.yelp_aac.presentation.utils.ThemeManager
import com.puzzlebench.yelp_aac.repository.BusinessDetailsRepository
import com.puzzlebench.yelp_aac.repository.BusinessRepository
import com.puzzlebench.yelp_aac.repository.UpdateRepository

class YelpApplication : Application() {
    // TODO this can be improvement this using Dagger2 whit the Scopes
    val businessRepository: BusinessRepository
        get() = ServiceLocator.provideBusinessRepository(this)

    val updateRepository: UpdateRepository
        get() = ServiceLocator.provideUpdateRepository(this)

    val businessDetailsRepository: BusinessDetailsRepository
        get() = ServiceLocator.provideBusinessDetailsRepository(this)

    override fun onCreate() {
        super.onCreate()
        if (BuildConfig.DEBUG){
            Stetho.initializeWithDefaults(this)
        }
        initSyncAlarm()
        initTheme()
    }

    private fun initTheme() {
        val preferences = PreferenceManager.getDefaultSharedPreferences(this)
        ThemeManager.applyTheme(preferences.getString("preference_key_theme", "")!!)
    }

    private fun initSyncAlarm() {
        AlarmManagerHelper(applicationContext).initSyncAlarm()
    }
}
