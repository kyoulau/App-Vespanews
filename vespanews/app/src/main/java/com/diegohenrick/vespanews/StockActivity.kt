package com.diegohenrick.vespanews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.diegohenrick.vespanews.databinding.ActivityDataBinding
import com.diegohenrick.vespanews.databinding.ActivityStockBinding
import com.diegohenrick.vespanews.feature.data.local.API.NewsAPI
import com.diegohenrick.vespanews.feature.data.local.adapter.NewsAdapter
import com.diegohenrick.vespanews.feature.data.local.adapter.StocksAdapter
import com.diegohenrick.vespanews.feature.data.local.entity.Singleton
import com.diegohenrick.vespanews.feature.data.local.entity.StocksSingleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StockActivity : AppCompatActivity() {
    private lateinit var binding: ActivityStockBinding

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.stockdata.org/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val stocksAPI = retrofit.create(NewsAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = DataBindingUtil.setContentView(this, R.layout.activity_stock)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.IO).launch {
            StocksSingleton.setContext(this@StockActivity)

            if (StocksSingleton.stocksList.isEmpty()) {
                val stocks = withContext(Dispatchers.IO) {
                    stocksAPI.getStocksAPI()
                }
                for (stock in stocks) {
                    StocksSingleton.addStocks(stock)
                }
            }
            binding.stocksRecyclerView.adapter = StocksAdapter()
            binding.stocksRecyclerView.layoutManager = LinearLayoutManager(this@StockActivity)

        }
    }
}