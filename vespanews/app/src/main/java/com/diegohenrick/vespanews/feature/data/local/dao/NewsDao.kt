package com.diegohenrick.vespanews.feature.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow

//all queries must be executed on a separate thread.

@Dao
interface NewsDao {

    @Query("SELECT * FROM Noticia")
    fun getAllNews(): List<NewsEntity>

    @Query("SELECT * FROM Noticia WHERE id = :id")
    fun getNewsById(id:Int): NewsEntity?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insertNews(newsEntity: NewsEntity)

    @Update
    fun updateNews(newsEntity: NewsEntity)

    //@Upsert
    //method wil make possible make an update and inser new fields
    //fun upsertNews(newsEntity: NewsEntity)

    @Delete
    //method for delete a news
    fun deleteNews(newsEntity: NewsEntity)

    @Query("SELECT * FROM Noticia")
    fun getNews(): Flow<List<NewsEntity>>

    @Query("DELETE FROM Noticia")
    fun deleteAllNews()
}