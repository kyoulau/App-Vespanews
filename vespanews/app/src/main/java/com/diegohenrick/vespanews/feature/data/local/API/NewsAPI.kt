package com.diegohenrick.vespanews.feature.data.local.API
import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface NewsAPI {

    @GET("https://api.stockdata.org/v1/data/quote?symbols=AAPL,TSLA,MSFT&api_token=zRFxefSLQYlknGaqTyLORfWH6dHWROkR0HeZVOwH")
    suspend fun getNews(): Flow<List<NewsEntity>>

    @GET("news/all HTTP/1.1")
    suspend fun getAllNews(): Flow<List<NewsEntity>>

}