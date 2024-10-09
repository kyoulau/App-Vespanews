package com.diegohenrick.vespanews.feature.data.local.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.diegohenrick.vespanews.databinding.StockViewBinding
import com.diegohenrick.vespanews.feature.data.local.entity.StocksEntity
import com.diegohenrick.vespanews.feature.data.local.entity.StocksSingleton

class StocksAdapter() : RecyclerView.Adapter<StocksAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: StockViewBinding) : RecyclerView.ViewHolder(binding.root){
        @SuppressLint("SetTextI18n")
        fun bind(stocksEntity: StocksEntity){
            binding.companyTicker.text = stocksEntity.ticker
            binding.companyName.text = stocksEntity.name
            binding.companyPrice.text = stocksEntity.price.toString()
            binding.companyChange.text = stocksEntity.day_change.toString()

            if (stocksEntity.day_change > 0) {
                binding.companyChange.setTextColor(binding.root.context.getColor(android.R.color.holo_green_light))
            } else {
                binding.companyChange.setTextColor(binding.root.context.getColor(android.R.color.holo_red_light))
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = StockViewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return StocksSingleton.stocksList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val stocksEntity = StocksSingleton.stocksList[position]
        holder.bind(stocksEntity)
    }
}