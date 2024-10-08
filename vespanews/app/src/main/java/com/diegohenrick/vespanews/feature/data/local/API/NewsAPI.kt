package com.diegohenrick.vespanews.feature.data.local.API
import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import com.diegohenrick.vespanews.feature.data.local.entity.NewsList
import com.diegohenrick.vespanews.feature.data.local.entity.Singleton
import com.diegohenrick.vespanews.feature.data.local.entity.StockList
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface NewsAPI {

    @GET("news/all?language=en&api_token=zRFxefSLQYlknGaqTyLORfWH6dHWROkR0HeZVOwH")
    suspend fun getNewsAPI(): NewsList

    @GET("data/quote?symbols=AAPL,TSLA,MSFT&api_token=zRFxefSLQYlknGaqTyLORfWH6dHWROkR0HeZVOwH")
    suspend fun getStockAPI(): StockList

}