package com.example.gojekassignment

import android.app.Application
import com.example.samplelibrary.koindi.myModule
import com.imagedemo.di.retrofitModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class GojekApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        startKoin {
            androidContext(this@GojekApplication)
            androidLogger()
            modules(listOf(myModule, retrofitModule))
        }
    }
}