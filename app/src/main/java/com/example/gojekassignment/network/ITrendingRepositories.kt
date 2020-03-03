package com.example.gojekassignment.network

import com.example.gojekassignment.TrendingRepositoriesModel
import com.example.gojekassignment.viewmodel.ApiResult
import io.reactivex.Observable
import retrofit2.http.GET

interface ITrendingRepositories : ApiResult {

    @GET("repositories?language=&since=daily")
    fun getData(): Observable<MutableList<TrendingRepositoriesModel>>
}