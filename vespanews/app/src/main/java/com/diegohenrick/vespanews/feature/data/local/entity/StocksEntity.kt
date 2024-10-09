package com.diegohenrick.vespanews.feature.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull

@Entity(tableName = "stocks")
data class StocksEntity(

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @NotNull
    @ColumnInfo("nome-acao")
    val name: String,
    @NotNull
    @ColumnInfo("preco-acao")
    val price: Double,
    @NotNull
    @ColumnInfo("variacao-acao")
    val day_change: Double,

    @NotNull
    @ColumnInfo("simbolo-acao")
    val ticker: String
)

data class StocksList(
    val data: List<StocksEntity>
)