package com.diegohenrick.vespanews.feature.data.local.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.diegohenrick.vespanews.databinding.NewsViewBinding
import com.diegohenrick.vespanews.feature.data.local.entity.NewsEntity
import com.diegohenrick.vespanews.feature.data.local.entity.Singleton

class NewsAdapter() : RecyclerView.Adapter<NewsAdapter.ViewHolder>() {

    inner class ViewHolder(val binding: NewsViewBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(newsEntity: NewsEntity){
            binding.title.text = newsEntity.title
            binding.description.text = newsEntity.description

            Glide.with(binding.imageView3.context)
                .load(newsEntity.image_url).into(binding.imageView3)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = NewsViewBinding.inflate(LayoutInflater.
        from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount() : Int = Singleton.data.size


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val newsEntity = Singleton.data[position]
        holder.bind(newsEntity)
    }
}