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

//    companion object {
////        const val DATABASE_NAME = "vespa_db"
////        private var instance: NewsRoomDatabase? = null
//    }
//
//    @Volatile
//    private var INSTANCE: NewsRoomDatabase? = null
//
//    fun getDatabase(context: Context): NewsRoomDatabase {
//        return INSTANCE ?: synchronized(this){
//            Room.databaseBuilder(context, NewsRoomDatabase::class.java,"item_database")
//                .build()
//                //allowMainThreadQueries()
//                .also { INSTANCE = it }
//        }
//    }
    companion object {
         const val DATABASE_NAME = "vespa_db"
         private var instance: NewsRoomDatabase? = null
         fun getInstance(context:Context) :NewsRoomDatabase?{
             if(instance == null){
                synchronized(NewsRoomDatabase::class){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        NewsRoomDatabase::class.java,
                        DATABASE_NAME).allowMainThreadQueries().build()
                }
             }
             return instance!!
         }
    }



}