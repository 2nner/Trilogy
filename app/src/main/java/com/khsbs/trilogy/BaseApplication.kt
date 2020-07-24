package com.khsbs.trilogy

import android.app.Application
import android.content.Context
import com.facebook.stetho.Stetho
import timber.log.Timber

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        context = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }

    companion object {
        lateinit var context: Context
    }
}