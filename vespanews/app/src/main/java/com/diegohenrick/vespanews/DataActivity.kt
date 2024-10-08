package com.diegohenrick.vespanews

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.GridLayoutManager
import com.diegohenrick.vespanews.databinding.ActivityDataBinding
import com.diegohenrick.vespanews.feature.data.factory.DataViewModelFactory
import com.diegohenrick.vespanews.feature.data.local.API.NewsAPI
import com.diegohenrick.vespanews.feature.data.local.adapter.NewsAdapter
import com.diegohenrick.vespanews.feature.data.local.entity.Singleton
import com.diegohenrick.vespanews.feature.data.local.viewModel.DataViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


class DataActivity : AppCompatActivity() {

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://api.stockdata.org/v1/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val newsAPI = retrofit.create(NewsAPI::class.java)
    private lateinit var binding: ActivityDataBinding
    lateinit var viewModel: DataViewModel

    private lateinit var sharedPreferences: SharedPreferences


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        binding = DataBindingUtil.setContentView<ActivityDataBinding>(this, R.layout.activity_data)
        viewModel = DataViewModelFactory().create(DataViewModel::class.java)

        sharedPreferences = getSharedPreferences("API_PREFS", Context.MODE_PRIVATE)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
         }

        Singleton.setContext(this)

        if(shouldFetchNewData()){
            fetchNewsData()
            saveLastApiCallTime()
            logLastApiCallTime()
        }
        binding.newsRecyclerView.adapter = NewsAdapter()
        binding.newsRecyclerView.layoutManager = GridLayoutManager(this@DataActivity, 2)

    }

    private fun fetchNewsData() {
         CoroutineScope(Dispatchers.Main).launch {

            val newsResponse = withContext(Dispatchers.IO) {
                newsAPI.getNewsAPI()
            }
            newsResponse.data.forEach { news ->
                viewModel.addNews(news)
            }
             binding.newsRecyclerView.adapter?.notifyDataSetChanged()
        }
    }

    private fun saveLastApiCallTime() {
        val editor = sharedPreferences.edit()
        editor.putLong("last_api_call_time", System.currentTimeMillis()) // Armazena a data/hora atual em milissegundos
        editor.apply()
    }

    private fun getLastApiCallTime(): Long {
        return sharedPreferences.getLong("last_api_call_time", 0)
    }

    private fun shouldFetchNewData(): Boolean {
        val lastCallTime = getLastApiCallTime()
        val currentTime = System.currentTimeMillis()
        val oneMinuteInMillis = 300 * 1000

        return (currentTime - lastCallTime) > oneMinuteInMillis // Retorna true se já passou mais de 1 minuto
    }

    private fun logLastApiCallTime() {
        val lastCallTime = getLastApiCallTime() // Obtém o horário salvo em milissegundos

        if (lastCallTime != 0L) {  // Verifica se o horário foi salvo
            val date = Date(lastCallTime) // Converte para uma data
            val format = SimpleDateFormat("dd/MM/yyyy HH:mm:ss", Locale.getDefault()) // Define o formato de data/hora

            val formattedDate = format.format(date) // Formata a data
            Log.d("API_CALL", "Última chamada à API foi em: $formattedDate") // Mostra no Logcat
        } else {
            Log.d("API_CALL", "Nenhuma chamada à API foi feita ainda.")
        }
    }
}
