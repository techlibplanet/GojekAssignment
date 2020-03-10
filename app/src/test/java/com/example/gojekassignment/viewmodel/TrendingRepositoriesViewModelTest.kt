package com.example.gojekassignment.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.gojekassignment.TrendingRepositoriesModel
import com.example.gojekassignment.network.ITrendingRepositories
import com.example.gojekassignment.rules.RxSchedulersOverrideRule
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TrendingRepositoriesViewModelTest {
    @Mock
    private lateinit var trendingRepositoryService: ITrendingRepositories

    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var apiResult: ApiResult

    @get:Rule
    val taskExecutorRule = InstantTaskExecutorRule()

    private val testObject by lazy {
        TrendingRepositoriesViewModel(trendingRepositoryService)
    }

    @get:Rule
    var rxSchedulersOverrideRule =
        RxSchedulersOverrideRule()

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
    }


    @Test
    fun `check api calls OnSuccess`() {
        //Arrange
        val trendingData = mutableListOf<TrendingRepositoriesModel>()
        whenever(trendingRepositoryService.getData()).thenReturn(Observable.just(trendingData))
        // ACt
        testObject.getTrendingRepositories(apiResult)
        // Assert
        verify(apiResult).onSuccess(trendingData)

    }

    @Test
    fun `check api calls onError`() {
        //Arrange
        whenever(trendingRepositoryService.getData()).thenReturn(Observable.error(Throwable("jkashk")))
        // ACt
        testObject.getTrendingRepositories(apiResult)
        // Assert
        verify(apiResult).onError("jkashk")

    }

    @After
    fun after() {
        RxAndroidPlugins.reset()
    }
}