package com.example.samplelibrary.koindi

import com.example.gojekassignment.viewmodel.TrendingRepositoriesViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule =
    module(override = true) {

        viewModel {
            TrendingRepositoriesViewModel(trendingRepositoryService = get())
        }
    }
