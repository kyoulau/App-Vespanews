package com.diegohenrick.vespanews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.diegohenrick.vespanews.databinding.ActivityDataBinding
import com.diegohenrick.vespanews.feature.data.local.API.NewsAPI
import com.diegohenrick.vespanews.feature.data.local.adapter.NewsAdapter
import com.diegohenrick.vespanews.feature.data.local.entity.Singleton
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StockActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.stockdata.org/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val newsAPI = retrofit.create(NewsAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = DataBindingUtil.setContentView<ActivityDataBinding>(this, R.layout.activity_stock)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        CoroutineScope(Dispatchers.Main).launch {
            var stock = withContext(Dispatchers.IO) {
                newsAPI.getStockAPI()
            }

            binding.newsRecyclerView.layoutManager = GridLayoutManager(this@StockActivity, 2)
        }
    }
}