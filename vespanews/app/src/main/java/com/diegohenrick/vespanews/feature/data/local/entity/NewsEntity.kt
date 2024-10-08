package com.diegohenrick.vespanews.feature.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.android.material.floatingactionbutton.FloatingActionButton.Size
import org.jetbrains.annotations.NotNull
import java.util.Date

@Entity(tableName = "Noticia")
data class NewsEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @NotNull
    @ColumnInfo("titulo_noticia")
    val title: String,

    @NotNull
    @ColumnInfo("descricao_noticia")
    val description: String,


    @NotNull
    @ColumnInfo("imagem-noticia")
    val image_url: String


)

data class NewsList(
    val data: List<NewsEntity>


)

