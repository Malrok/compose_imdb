package com.mrk.composeimdb

import android.app.Application
import timber.log.Timber
import timber.log.Timber.DebugTree


class TmdbApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(DebugTree())
    }

}