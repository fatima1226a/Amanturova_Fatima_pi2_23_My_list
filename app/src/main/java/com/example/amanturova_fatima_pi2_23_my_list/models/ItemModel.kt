package com.example.amanturova_fatima_pi2_23_my_list.models


import androidx.room.ColumnInfo
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Entity
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.PrimaryKey
import androidx.room.Query



@Entity(tableName = "table_records")
data class ItemModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,

    @ColumnInfo(name = "news_title")
    val news_title: String,

    @ColumnInfo(name = "simple_text")
    val simple_text: String,

    @ColumnInfo(name = "news_image", typeAffinity = ColumnInfo.BLOB)
    val news_img: ByteArray,
)