package com.example.cleanarchslvglass.data.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.cleanarchslvglass.data.models.SettingsModel
import kotlinx.coroutines.flow.Flow

@Dao
interface SettingsDao {

    @Query("SELECT * FROM settings_table")
    fun getSettings(): Flow<List<SettingsModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(settingsModel: SettingsModel)

    @Query("UPDATE settings_table SET notificationState=:notificationState")
    suspend fun updateNotification(notificationState: Int)

}