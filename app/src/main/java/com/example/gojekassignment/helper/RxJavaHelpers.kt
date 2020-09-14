package com.example.gojekassignment.helper

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable
import io.reactivex.rxjava3.schedulers.Schedulers
import timber.log.Timber


inline fun <T> Observable<T>.processRequest(
    crossinline onNext: (result: T) -> Unit,
    crossinline onError: (message: String?) -> Unit
): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { result ->
                onNext(result)
            },
            { err ->
                val message = ProcessThrowable.getMessage(err)
                Timber.e(err)
                onError(message)
            }
        )
}

inline fun <T> Single<T>.processRequest(
    crossinline onSuccess: (result: T) -> Unit,
    crossinline onError: (message: String?) -> Unit
): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { result ->
                onSuccess(result)
            },
            { err ->
                val message = ProcessThrowable.getMessage(err)
                Timber.e(err)
                onError(message)
            }
        )
}

inline fun <T> Single<T>.processRequest(crossinline onSuccess: (result: T) -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { result ->
                onSuccess(result)
            }
        )
}

fun <T> Single<T>.processRequest(): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}

inline fun <T> Flowable<T>.processRequest(
    crossinline onNext: (result: T) -> Unit,
    crossinline onError: (message: String?) -> Unit
): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { result ->
                onNext(result)
            },
            { err ->
                val message = ProcessThrowable.getMessage(err)
                Timber.e(err)
                onError(message)
            }
        )
}

inline fun <T> Flowable<T>.processRequest(crossinline onNext: (result: T) -> Unit): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
            { result ->
                onNext(result)
            }
        )
}

fun <T> Flowable<T>.processRequest(): Disposable {
    return subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe()
}