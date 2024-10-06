package com.diegohenrick.vespanews

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.diegohenrick.vespanews.databinding.ActivityDataBinding
import com.diegohenrick.vespanews.databinding.ActivityMainBinding
import com.diegohenrick.vespanews.feature.data.local.API.NewsAPI
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class DataActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.stockdata.org/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val newsAPI = retrofit.create(NewsAPI::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val binding = DataBindingUtil.setContentView<ActivityDataBinding>(this, R.layout.activity_data)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        CoroutineScope(Dispatchers.Main).launch {
            val news = newsAPI.getNews()
            runOnUiThread{
                binding.title.text = news.toString()
            }
        }
    }
}