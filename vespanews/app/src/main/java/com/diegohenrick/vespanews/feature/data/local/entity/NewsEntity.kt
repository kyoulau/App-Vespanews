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
    val tittle: String,

    @NotNull
    @ColumnInfo("descricao_noticia")
    val description: String,

    @ColumnInfo("data_noticia")
    val data: String
)
