package com.diegohenrick.vespanews.feature.data.local.repository

import com.diegohenrick.vespanews.feature.data.local.dao.NewsDao
import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

class OffLineNewsRepository(private val newsDao: NewsDao): NewsRepository {
    override fun getAllNews(): Flow<NewsEntity> = newsDao.getAllNews()

    override suspend fun getNewsById(id: Int): NewsEntity? = newsDao.getNewsById(id)

    override suspend fun insertNews(newsEntity: NewsEntity): Long = newsDao.insertNews(newsEntity)

    override suspend fun updateNews(newsEntity: NewsEntity) = newsDao.updateNews(newsEntity)

    override suspend fun deleteNews(newsEntity: NewsEntity) = newsDao.deleteNews(newsEntity)

    override fun getNews(): Flow<List<NewsEntity>>  = newsDao.getNews()
}
