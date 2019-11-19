package com.example.gojekassignment

import android.app.Application
import com.example.gojekassignment.dependency.components.ApplicationComponent
import com.example.gojekassignment.dependency.components.DaggerApplicationComponent
import com.example.gojekassignment.dependency.modules.AppContextModule
import timber.log.Timber

class GojekApplication : Application() {

    companion object {
        lateinit var applicationComponent: ApplicationComponent
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }

        applicationComponent = DaggerApplicationComponent.builder()
            .appContextModule(AppContextModule(applicationContext))
            .build()
    }
}