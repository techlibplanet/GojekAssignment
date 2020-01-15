package com.example.gojekassignment

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gojekassignment.dependency.components.DaggerInjectActivityComponent
import com.example.gojekassignment.helper.processRequest
import com.example.gojekassignment.network.ITrendingRepositories
import com.example.gojekassignment.trendingrepository.TrendingRepositoryAdapter
import com.example.gojekassignment.viewmodel.TrendingRepositories
import com.facebook.shimmer.ShimmerFrameLayout
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject


class MainActivity : AppCompatActivity() {

    private lateinit var disposable: CompositeDisposable
    @Inject
    lateinit var trendingRepositoryService: ITrendingRepositories
    private val trendingRepositoryAdapter: TrendingRepositoryAdapter by lazy { TrendingRepositoryAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val depComponent = DaggerInjectActivityComponent.builder()
            .applicationComponent(GojekApplication.applicationComponent)
            .build()
        depComponent.injectMainActivity(this)

        disposable = CompositeDisposable()

        trending_repository_recycler_view.layoutManager = LinearLayoutManager(this)
        trending_repository_recycler_view.setHasFixedSize(true)
        trending_repository_recycler_view.addItemDecoration(
            DividerItemDecoration(
                this,
                DividerItemDecoration.VERTICAL
            )
        )
        trending_repository_recycler_view.adapter = trendingRepositoryAdapter

        pullToRefresh.setOnRefreshListener {
            shimmer_view_container.visibility = View.VISIBLE;
            shimmer_view_container.startShimmer()
            setData()
            pullToRefresh.isRefreshing = false
        }

        setData()
    }

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        shimmer_view_container.stopShimmer()
    }

    private fun setData() {
        disposable.add(trendingRepositoryService.getData().processRequest(
            {
                Timber.d(it.toString())
                setTrendingRepositoryAdapter(it)
            }, {
                Timber.d(it)
            }
        ))
    }

    private fun setTrendingRepositoryAdapter(trendingRepositories: MutableList<TrendingRepositories>) {
        trendingRepositoryAdapter.items = trendingRepositories
        trendingRepositoryAdapter.notifyDataSetChanged()
        shimmer_view_container.stopShimmer();
        shimmer_view_container.visibility = View.GONE;
    }
}
