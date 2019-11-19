package com.example.gojekassignment

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.gojekassignment.dependency.components.DaggerInjectActivityComponent
import com.example.gojekassignment.helper.processRequest
import com.example.gojekassignment.network.ITrendingRepositories
import io.reactivex.disposables.CompositeDisposable
import timber.log.Timber
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private val TAG = MainActivity::class.java.simpleName
    private lateinit var disposable: CompositeDisposable
    @Inject
    lateinit var trendingRepositoryService: ITrendingRepositories

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val depComponent = DaggerInjectActivityComponent.builder()
            .applicationComponent(GojekApplication.applicationComponent)
            .build()
        depComponent.injectMainActivity(this)

        disposable = CompositeDisposable()


    }

    private fun getData() {
        disposable.add(trendingRepositoryService.getData().processRequest(
            {
                Timber.d(it.toString())
            }, {
                Timber.d(it)
            }
        ))
    }
}
