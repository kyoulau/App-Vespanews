package com.diegohenrick.vespanews.feature.data.local.entity

import android.content.Context
import com.diegohenrick.vespanews.feature.data.database.StocksRoomDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.StocksDAO

object StocksSingleton {
    lateinit var stocksList : List<StocksEntity>
    private lateinit var  dao : StocksDAO

    fun setContext(context: Context) {
        StocksRoomDatabase.getInstance(context)?.let {
            dao = it.stocksDAO()
            stocksList = dao.getAllStocks()
        }
    }

    fun addStocks(stocksEntity: StocksEntity) {
        dao.insertStocks(stocksEntity)
        stocksList = dao.getAllStocks()
    }

    fun deleteAllStocks() {
        dao.deleteAllStocks()
        stocksList = dao.getAllStocks()
    }
}