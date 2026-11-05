package com.example.amanturova_fatima_pi2_23_my_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amanturova_fatima_pi2_23_my_list.models.ItemModel
import com.example.amanturova_fatima_pi2_23_my_list.room.RecordDao
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class HomeViewModel @Inject constructor(
    private val recordDao: RecordDao
) : ViewModel() {

    fun insert(itemModel: ItemModel) {
        viewModelScope.launch {
            recordDao.insert(itemModel)

        }
    }

    fun getAllItems(): List<ItemModel> {
        return recordDao.getAllItems()
    }
}