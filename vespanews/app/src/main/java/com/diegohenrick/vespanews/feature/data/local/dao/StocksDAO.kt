package com.diegohenrick.vespanews.feature.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.diegohenrick.vespanews.feature.data.local.entity.StocksEntity

@Dao
interface StocksDAO {
    @Query("SELECT * FROM Stocks")
    fun getAllStocks(): List<StocksEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStocks(stocksEntity: StocksEntity)

    @Update
    fun updateStocks(stocksEntity: StocksEntity)

    @Query("DELETE FROM Stocks")
    fun deleteAllStocks()


}