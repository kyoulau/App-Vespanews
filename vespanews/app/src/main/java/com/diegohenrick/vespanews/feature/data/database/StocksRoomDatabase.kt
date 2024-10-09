package com.diegohenrick.vespanews.feature.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.StocksDAO
import com.diegohenrick.vespanews.feature.data.local.entity.StocksEntity

@Database(entities = [StocksEntity::class], version = 3, exportSchema = false)
abstract class StocksRoomDatabase: RoomDatabase() {
    abstract fun stocksDAO(): StocksDAO

    companion object {
        const val DATABASE_NAME = "stocks_db"

        private var instance: StocksRoomDatabase? = null

        fun getInstance(context: Context) :StocksRoomDatabase?{
            if (instance == null) {
                synchronized(StocksRoomDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        StocksRoomDatabase::class.java,
                        DATABASE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build()
                }
            }
            return instance!!
        }


    }
}