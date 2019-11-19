package com.example.gojekassignment.network

import com.example.gojekassignment.viewmodel.TrendingRepositories
import io.reactivex.Observable
import retrofit2.http.GET

interface ITrendingRepositories {

    @GET("repositories?language=&since=daily")
    fun getData() : Observable<MutableList<TrendingRepositories>>
}