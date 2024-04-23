package com.chorokbul.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

class ChorokbulApp : Application() {
    override fun onCreate() {
        super.onCreate()

        settingTimber()
    }

    private fun settingTimber() {
        Timber.plant(Timber.DebugTree())
    }
}