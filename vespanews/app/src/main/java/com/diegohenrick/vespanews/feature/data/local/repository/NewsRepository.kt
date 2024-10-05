package com.diegohenrick.vespanews.feature.data.local.repository

import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

// ornecer entidades get, insert, delete e update do banco de dados.
interface NewsRepository {

    fun getAllNews(): Flow<NewsEntity>

    suspend fun getNewsById(id:Int): NewsEntity?

    suspend fun insertNews(newsEntity: NewsEntity): Long

    suspend fun updateNews(newsEntity: NewsEntity)

    suspend fun deleteNews(newsEntity: NewsEntity)

    suspend fun getNews(): Flow<List<NewsEntity>>
}