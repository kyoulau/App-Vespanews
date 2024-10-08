package com.diegohenrick.vespanews.feature.data.local.entity

import android.content.Context
import com.diegohenrick.vespanews.feature.data.database.NewsRoomDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.NewsDao
import java.sql.Time

object Singleton {
    lateinit var data: List<NewsEntity>
    private lateinit var dao: NewsDao

    fun setContext(context: Context) {
        NewsRoomDatabase.getInstance(context)?.let {
            dao = it.newsDao()
            data = dao.getAllNews()
            }
    }

    fun deleteAllNews() {
        dao.deleteAllNews()
        data = dao.getAllNews()
    }

    suspend fun addNews(news: NewsEntity) {
        dao.insertNews(news)
        data = dao.getAllNews()
    }
}

