package com.diegohenrick.vespanews.feature.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.NewsDao
import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import kotlinx.coroutines.CoroutineScope

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
public abstract class NewsRoomDatabase: RoomDatabase() {
    abstract fun newsDao(): NewsDao

    companion object {}

    @Volatile
    private var INSTANCE: NewsRoomDatabase? = null

    fun getDatabase(
        context: Context,
    ): NewsRoomDatabase {
        return INSTANCE ?: synchronized(this){
            Room.databaseBuilder(context, NewsRoomDatabase::class.java,"item_database")
                .build()
                .also { INSTANCE = it }
        }
    }
}