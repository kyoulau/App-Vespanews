package com.diegohenrick.vespanews.feature.data.local.entity

import android.content.Context
import com.diegohenrick.vespanews.feature.data.database.NewsRoomDatabase
import com.diegohenrick.vespanews.feature.data.local.dao.NewsDao

object Singleton {
    lateinit var data: List<NewsEntity>
    private lateinit var dao: NewsDao

    fun setContext(context: Context) {
        NewsRoomDatabase.getInstance(context)?.let {
            dao = it.newsDao()
            data = dao.getAllNews()
            }
    }

    suspend fun updateDataFromApi(apiResponse: NewsList) {

        for (item in apiResponse.data) {
            dao.insertNews(item)
        }
        data = dao.getAllNews()
    }

    suspend fun deleteAllNews() {
        dao.deleteAllNews()
        data = dao.getAllNews()
    }
}

