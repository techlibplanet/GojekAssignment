package com.example.gojekassignment.trendingrepository

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gojekassignment.TrendingRepositoriesModel
import com.example.gojekassignment.databinding.TrendingRepositoriesBinding
import kotlinx.android.synthetic.main.trending_repository_items.view.*

class TrendingRepositoryAdapter : RecyclerView.Adapter<TrendingRepositoryViewHolder>() {

    var items: List<TrendingRepositoriesModel> = emptyList()
    private lateinit var context: Context
    private lateinit var dataBinding: TrendingRepositoriesBinding

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TrendingRepositoryViewHolder {
        context = parent.context
        val inflater = LayoutInflater.from(context)
        dataBinding = TrendingRepositoriesBinding.inflate(inflater, parent, false)
        return TrendingRepositoryViewHolder(dataBinding)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: TrendingRepositoryViewHolder, position: Int) {
        holder.bind(items[position], position)
    }
}