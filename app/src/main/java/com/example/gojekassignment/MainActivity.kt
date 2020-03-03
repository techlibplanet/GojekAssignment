package com.example.gojekassignment

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.gojekassignment.helper.NetworkHelper
import com.example.gojekassignment.trendingrepository.TrendingRepositoryAdapter
import com.example.gojekassignment.viewmodel.ApiResult
import com.example.gojekassignment.viewmodel.TrendingRepositoriesViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity(), ApiResult {

    private val trendingRepositoryAdapter: TrendingRepositoryAdapter by lazy { TrendingRepositoryAdapter() }
    val viewModel by viewModel<TrendingRepositoriesViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


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
            when {
                NetworkHelper.isNetworkConnected(this) -> viewModel.getTrendingRepositories(this)
                else -> showToastMessage("No Internet !")
            }
            pullToRefresh.isRefreshing = false
        }

        viewModel.getTrendingRepositories(this)
    }

    override fun onResume() {
        super.onResume()
        shimmer_view_container.startShimmer()
    }

    override fun onPause() {
        super.onPause()
        shimmer_view_container.stopShimmer()
    }

    private fun setTrendingRepositoryAdapter(trendingRepositories: MutableList<TrendingRepositoriesModel>) {
        trendingRepositoryAdapter.items = trendingRepositories
        trendingRepositoryAdapter.notifyDataSetChanged()
        shimmer_view_container.stopShimmer();
        shimmer_view_container.visibility = View.GONE;
    }

    override fun onSuccess(data: Any) {
        setTrendingRepositoryAdapter(data as MutableList<TrendingRepositoriesModel>)
    }

    override fun onError(error: String) {
        showToastMessage(error)
    }

    private fun showToastMessage(message: String) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}
