package com.example.gojekassignment.dependency.components

import android.content.Context
import com.example.gojekassignment.database.GojekDatabase
import com.example.gojekassignment.dependency.modules.AppContextModule
import com.example.gojekassignment.dependency.modules.DatabaseModule
import com.example.gojekassignment.dependency.modules.NetworkApiModule
import com.example.gojekassignment.dependency.qualifiers.ApplicationContextQualifier
import com.example.gojekassignment.dependency.scopes.ApplicationScope
import com.example.gojekassignment.network.ITrendingRepositories
import dagger.Component

@ApplicationScope
@Component(
    modules = [AppContextModule::class, DatabaseModule::class, NetworkApiModule::class]
)
interface ApplicationComponent {

    @ApplicationContextQualifier
    fun getAppContext(): Context

    fun getDatabase(): GojekDatabase

    fun getTrendingRepositories(): ITrendingRepositories


}