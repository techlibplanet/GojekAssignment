package com.example.gojekassignment.dependency.modules

import com.example.gojekassignment.dependency.scopes.ApplicationScope
import com.example.gojekassignment.helper.Constants.API_BASE_URL
import com.example.gojekassignment.helper.Constants.CONNECTION_TIMEOUT
import com.example.gojekassignment.helper.Constants.READ_TIMEOUT
import dagger.Module
import dagger.Provides
import timber.log.Timber

@Module
class HttpModule {
    @Provides
    @ApplicationScope
    fun loggingInterceptor(): okhttp3.logging.HttpLoggingInterceptor {
        val interceptor = okhttp3.logging.HttpLoggingInterceptor(okhttp3.logging.HttpLoggingInterceptor.Logger { message -> Timber.i(message) })
        interceptor.level = okhttp3.logging.HttpLoggingInterceptor.Level.BASIC
        return interceptor
    }

    @Provides
    @ApplicationScope
    fun okhttpIntersepter(): okhttp3.Interceptor {
        return okhttp3.Interceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
            chain.proceed(requestBuilder.build())
        }
    }

    @Provides
    @ApplicationScope
    fun okHttpClient(interceptor: okhttp3.Interceptor, loggingInterceptor: okhttp3.logging.HttpLoggingInterceptor): okhttp3.OkHttpClient {
        return okhttp3.OkHttpClient.Builder()
                .readTimeout(READ_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .connectTimeout(CONNECTION_TIMEOUT, java.util.concurrent.TimeUnit.SECONDS)
                .addInterceptor(loggingInterceptor)
                .retryOnConnectionFailure(true)
                .build()
    }

    @Provides
    @ApplicationScope
    fun gson(): retrofit2.converter.gson.GsonConverterFactory = retrofit2.converter.gson.GsonConverterFactory.create(com.google.gson.GsonBuilder().setLenient().setDateFormat("yyyy-MM-dd'T'HH:mm:ss").create())

    @Provides
    @ApplicationScope
    fun rxJavaFactory(): retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory = retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory.create()

    @Provides
    @ApplicationScope
    fun retrofit(okHttpClient: okhttp3.OkHttpClient, rxJavaFactory: retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory, gson: retrofit2.converter.gson.GsonConverterFactory): retrofit2.Retrofit {
        return retrofit2.Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addCallAdapterFactory(rxJavaFactory)
                .addConverterFactory(gson)
                .client(okHttpClient)
                .build()
    }
}