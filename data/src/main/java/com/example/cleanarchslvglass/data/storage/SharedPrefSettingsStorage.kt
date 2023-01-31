package com.example.cleanarchslvglass.data.storage

import com.example.cleanarchslvglass.data.models.SettingsModel
import kotlinx.coroutines.flow.Flow

class SharedPrefSettingsStorage(private val settingsDao: SettingsDao): SettingsStorage {

    override fun getSettings(): Flow<List<SettingsModel>> {
        return settingsDao.getSettings()
    }

    override suspend fun insert(settingsModel: SettingsModel) {
        return settingsDao.insert(settingsModel)
    }

    override suspend fun updateNotificationState(state: Int) {
        settingsDao.updateNotification(notificationState = state)
    }

}