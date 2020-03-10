package com.example.gojekassignment.network

import com.example.gojekassignment.TrendingRepositoriesModel
import io.reactivex.Observable
import retrofit2.http.GET

interface ITrendingRepositories {

    @GET("repositories?language=&since=daily")
    fun getData(): Observable<MutableList<TrendingRepositoriesModel>>
}