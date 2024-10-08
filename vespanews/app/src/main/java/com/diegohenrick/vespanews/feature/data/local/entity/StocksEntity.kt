package com.diegohenrick.vespanews.feature.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "stocks")
data class StocksEntity(
    @PrimaryKey
    @ColumnInfo(name = "symbol")
    val symbol: String,
    val name: String,
    val price: Double,
    val dayChange: Double
)

data class StocksList(
    val data: List<StocksEntity>
)