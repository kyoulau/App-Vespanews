package com.diegohenrick.vespanews.feature.data.local.repository

import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

// Fornecer entidades get, insert, delete e update do banco de dados.
interface NewsRepository {

    fun getAllNews(): List<NewsEntity>

    suspend fun getNewsById(id:Int): NewsEntity?

    suspend fun insertNews(newsEntity: NewsEntity)

    suspend fun updateNews(newsEntity: NewsEntity)

    suspend fun deleteNews(newsEntity: NewsEntity)

    suspend fun getNews(): Flow<List<NewsEntity>>

    suspend fun deleteAllNews()
}