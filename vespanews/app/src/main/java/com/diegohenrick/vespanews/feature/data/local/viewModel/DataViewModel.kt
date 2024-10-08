package com.diegohenrick.vespanews.feature.data.local.viewModel

import androidx.lifecycle.MutableLiveData
import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import com.diegohenrick.vespanews.feature.data.local.entity.NewsList
import com.diegohenrick.vespanews.feature.data.local.entity.Singleton

class DataViewModel {
    val newsList = MutableLiveData<List<NewsEntity>>()

    suspend fun addNews(news: NewsEntity) {
        Singleton.addNews(news)
        newsList.value= Singleton.data
    }

    fun refreshNews() {
        newsList.value = Singleton.data
    }
}