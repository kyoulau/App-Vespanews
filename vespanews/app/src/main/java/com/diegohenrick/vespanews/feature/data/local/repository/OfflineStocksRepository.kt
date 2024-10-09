package com.diegohenrick.vespanews.feature.data.local.repository

import com.diegohenrick.vespanews.feature.data.local.dao.StocksDAO
import com.diegohenrick.vespanews.feature.data.local.entity.StocksEntity

class OfflineStocksRepository(private val stocksDAO: StocksDAO): StocksRepository {

    override fun getAllStocks(): List<StocksEntity> = stocksDAO.getAllStocks()

    override suspend fun insertStocks(stocksEntity: StocksEntity) = stocksDAO.insertStocks(stocksEntity)

    override suspend fun updateStocks(stocksEntity: StocksEntity) = stocksDAO.updateStocks(stocksEntity)

    override suspend fun deleteAllStocks() = stocksDAO.deleteAllStocks()
}