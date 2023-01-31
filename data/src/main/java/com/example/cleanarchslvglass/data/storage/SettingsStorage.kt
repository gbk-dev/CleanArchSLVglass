package com.example.cleanarchslvglass.data.storage

import com.example.cleanarchslvglass.data.models.SettingsModel
import kotlinx.coroutines.flow.Flow


interface SettingsStorage {

    fun getSettings(): Flow<List<SettingsModel>>

    suspend fun insert(settingsModel: SettingsModel)

    suspend fun updateNotificationState(state : Int)

}