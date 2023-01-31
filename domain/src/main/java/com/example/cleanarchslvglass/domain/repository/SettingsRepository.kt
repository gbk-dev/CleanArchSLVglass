package com.example.cleanarchslvglass.domain.repository

import com.example.cleanarchslvglass.domain.models.Settings
import kotlinx.coroutines.flow.Flow


interface SettingsRepository {

    fun getSettings(): Flow<List<Settings>>

    suspend fun insert(settings: Settings)

    suspend fun updateNotificationState(state: Int)

}