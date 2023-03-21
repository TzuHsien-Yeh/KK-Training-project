package com.example.sampleproject

import android.app.Application
import android.content.Context
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber
import kotlin.properties.Delegates

@HiltAndroidApp
class SampleApplication : Application() {
    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        // initialize timber
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    companion object {
        var instance: SampleApplication by Delegates.notNull()

        fun appContext(): Context {
            return instance.applicationContext
        }
    }
}