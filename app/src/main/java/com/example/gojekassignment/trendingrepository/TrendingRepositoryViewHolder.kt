package com.example.gojekassignment.trendingrepository

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gojekassignment.databinding.TrendingRepositoriesBinding
import com.example.gojekassignment.viewmodel.TrendingRepositories

class TrendingRepositoryViewHolder(private val dataBinding: TrendingRepositoriesBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    var rowIndex = -1
    fun bind(
        model: TrendingRepositories,
        position: Int
    ) {
        dataBinding.trendingRepository = model
        dataBinding.executePendingBindings()

//        dataBinding.root.setOnClickListener {
//
//            if (rowIndex == adapterPosition){
//                rowIndex = -1
//                dataBinding.expandableLayout.visibility == View.VISIBLE
//            }else{
//                rowIndex = adapterPosition
//            }
//
//            if (rowIndex==-1) dataBinding.expandableLayout.visibility =
//                View.GONE
//            else dataBinding.expandableLayout.visibility = View.VISIBLE
//        }
    }
}