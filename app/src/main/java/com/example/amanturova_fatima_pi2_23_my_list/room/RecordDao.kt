package com.example.amanturova_fatima_pi2_23_my_list.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.amanturova_fatima_pi2_23_my_list.models.ItemModel

@Dao
interface RecordDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(itemModel: ItemModel)

    @Delete
    fun delete(itemModel: ItemModel)

    @Query("SELECT * FROM table_records")
    fun getAllItems(): List<ItemModel>

}