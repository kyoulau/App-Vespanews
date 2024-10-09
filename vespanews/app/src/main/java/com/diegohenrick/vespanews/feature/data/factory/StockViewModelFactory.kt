package com.diegohenrick.vespanews.feature.data.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.diegohenrick.vespanews.feature.data.local.viewModel.StocksViewModel

class StockViewModelFactory : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(StocksViewModel::class.java)) {
            StocksViewModel() as T
        } else {
            throw IllegalArgumentException("ViewModel Not Found")
        }
    }
}