package com.example.amanturova_fatima_pi2_23_my_list.room


import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.amanturova_fatima_pi2_23_my_list.models.ItemModel


@Database(entities = [ItemModel::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {

    abstract fun recordDao(): RecordDao

}