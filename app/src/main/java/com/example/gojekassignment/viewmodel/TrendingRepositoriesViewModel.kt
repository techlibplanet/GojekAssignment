package com.example.gojekassignment.viewmodel

import androidx.lifecycle.ViewModel
import com.example.gojekassignment.helper.processRequest
import com.example.gojekassignment.network.ITrendingRepositories
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber

class TrendingRepositoriesViewModel(
    private val trendingRepositoryService: ITrendingRepositories
) : ViewModel() {
    private val disposable: CompositeDisposable = CompositeDisposable()

    fun getTrendingRepositories(callback: ApiResult) {
        disposable.add(trendingRepositoryService.getData().processRequest(
            {
                Timber.d(it.toString())
                callback.onSuccess(it)
            }, {
                Timber.d(it)
                it?.let { err -> callback.onError(err) }
            }
        ))
    }
}

interface ApiResult {
    fun onSuccess(data: Any)
    fun onError(error: String)
}
