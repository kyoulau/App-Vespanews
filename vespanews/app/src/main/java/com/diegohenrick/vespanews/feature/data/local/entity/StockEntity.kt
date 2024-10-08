package com.diegohenrick.vespanews.feature.data.local.entity

data class StockEntity(
    val name: String,
    val price: Double,
    val day_change: Double
)

data class StockList(
    val data: List<StockEntity>
)
