package com.example.gojekassignment.dependency.modules

import com.example.gojekassignment.dependency.scopes.ApplicationScope
import com.example.gojekassignment.network.ITrendingRepositories
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit

@Module(includes = [HttpModule::class])
class NetworkApiModule {

    @Provides
    @ApplicationScope
    fun gojek(retrofit: Retrofit): ITrendingRepositories {
        return retrofit.create(ITrendingRepositories::class.java)
    }

}