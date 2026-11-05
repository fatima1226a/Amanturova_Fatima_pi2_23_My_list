package com.example.amanturova_fatima_pi2_23_my_list

import android.content.Context
import androidx.room.Room
import com.example.amanturova_fatima_pi2_23_my_list.room.AppDataBase
import com.example.amanturova_fatima_pi2_23_my_list.room.RecordDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideRecordDatabase(@ApplicationContext context: Context):
            AppDataBase {
        return Room.databaseBuilder(
            context,
            AppDataBase::class.java,
            "records_database"
        )
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideRecordDao(database: AppDataBase): RecordDao {
        return database.recordDao()
    }
}