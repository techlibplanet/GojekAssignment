package com.example.gojekassignment.viewmodel

import com.example.gojekassignment.TrendingRepositoriesModel
import com.example.gojekassignment.network.ITrendingRepositories
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import io.reactivex.Observable
import io.reactivex.Scheduler
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.disposables.CompositeDisposable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner


@RunWith(MockitoJUnitRunner::class)
class TrendingRepositoriesViewModelTest {
    @Mock
    private lateinit var trendingRepositoryService: ITrendingRepositories
    private lateinit var testObject: TrendingRepositoriesViewModel
    @Mock
    private lateinit var compositeDisposable: CompositeDisposable

    @Mock
    private lateinit var apiResult: ApiResult

    @Mock
    private lateinit var scheduler : Scheduler

    //    @Mock
//    private val
    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testObject = TrendingRepositoriesViewModel(trendingRepositoryService)
    }

    @Test
    fun `check api`() {
        //Arrange
        val trendingData = mutableListOf<TrendingRepositoriesModel>()
        whenever(trendingRepositoryService.getData()).thenReturn(Observable.just(trendingData))
        //whenever(testObject.getTrendingRepositories(capture(apiResult)))
        // ACt
        testObject.getTrendingRepositories(apiResult)
        // Assert
        verify(apiResult).onSuccess(trendingData)

    }

    @After
    fun after() {

    }
}