package com.example.gojekassignment.trendingrepository

import androidx.recyclerview.widget.RecyclerView
import com.example.gojekassignment.TrendingRepositoriesModel
import com.example.gojekassignment.databinding.TrendingRepositoriesBinding

class TrendingRepositoryViewHolder(private val dataBinding: TrendingRepositoriesBinding) :
    RecyclerView.ViewHolder(dataBinding.root) {
    var rowIndex = -1
    fun bind(
        model: TrendingRepositoriesModel,
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