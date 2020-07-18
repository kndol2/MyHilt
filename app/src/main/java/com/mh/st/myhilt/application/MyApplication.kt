package com.mh.st.myhilt.application

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MyApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.d("onCreate")
    }
}