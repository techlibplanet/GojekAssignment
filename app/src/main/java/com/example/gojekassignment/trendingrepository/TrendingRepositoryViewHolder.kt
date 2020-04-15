package com.example.gojekassignment.trendingrepository

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gojekassignment.TrendingRepositoriesModel
import com.example.gojekassignment.databinding.TrendingRepositoriesBinding
import kotlinx.android.synthetic.main.trending_repository_items.view.*

class TrendingRepositoryViewHolder(private val dataBinding: TrendingRepositoriesBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    var rowIndex = -1
    fun bind(model: TrendingRepositoriesModel, position: Int) {
        dataBinding.trendingRepository = model
        dataBinding.executePendingBindings()

        itemView.setOnClickListener {
            if (rowIndex != position) {
                rowIndex = position
                itemView.expandable_layout.visibility = View.VISIBLE
            } else {
                rowIndex = -1
                itemView.expandable_layout.visibility = View.GONE
            }
        }
    }
}