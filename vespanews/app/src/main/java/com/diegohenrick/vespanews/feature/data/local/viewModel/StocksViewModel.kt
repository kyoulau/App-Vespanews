package com.diegohenrick.vespanews.feature.data.local.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.diegohenrick.vespanews.feature.data.local.entity.StocksEntity
import com.diegohenrick.vespanews.feature.data.local.entity.StocksSingleton

class StocksViewModel : ViewModel() {
    private val stocksList = MutableLiveData<List<StocksEntity>>()

    suspend fun addStocks(stocks: StocksEntity) {
        StocksSingleton.addStocks(stocks)
        stocksList.value = StocksSingleton.stocksList
    }

    fun refreshStocks() {
        stocksList.value = StocksSingleton.stocksList
    }
}