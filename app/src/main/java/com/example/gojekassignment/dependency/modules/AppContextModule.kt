package com.example.gojekassignment.dependency.modules

import android.content.Context
import com.example.gojekassignment.dependency.qualifiers.ApplicationContextQualifier
import com.example.gojekassignment.dependency.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module
class AppContextModule(@ApplicationContextQualifier val context: Context) {
    @Provides
    @ApplicationScope
    @ApplicationContextQualifier
    fun provideContext(): Context {
        return context
    }
}