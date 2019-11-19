package com.example.gojekassignment.dependency.modules

import android.content.Context
import androidx.room.Room
import com.example.gojekassignment.database.GojekDatabase
import com.example.gojekassignment.dependency.qualifiers.ApplicationContextQualifier
import com.example.gojekassignment.dependency.scopes.ApplicationScope
import dagger.Module
import dagger.Provides

@Module(includes = [AppContextModule::class])
class DatabaseModule {
    @Provides
    @ApplicationScope
    fun gojekDatabase(@ApplicationContextQualifier context: Context): GojekDatabase {
        return Room.databaseBuilder(context, GojekDatabase::class.java, "gojek-db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()
    }
}