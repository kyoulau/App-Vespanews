package com.diegohenrick.vespanews.feature.data.local.repository

import com.diegohenrick.vespanews.feature.data.local.entity.StocksEntity

// Fornecer entidades get, insert, delete e update do banco de dados.
interface StocksRepository {

    fun getAllStocks(): List<StocksEntity>

    suspend fun insertStocks(stocksEntity: StocksEntity)

    suspend fun updateStocks(stocksEntity: StocksEntity)

    suspend fun deleteAllStocks()
}