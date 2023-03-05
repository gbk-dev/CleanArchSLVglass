package com.example.cleanarchslvglass.data.repository

import com.example.cleanarchslvglass.data.models.SettingsModel
import com.example.cleanarchslvglass.data.storage.SettingsStorage
import com.example.cleanarchslvglass.domain.models.Settings
import com.example.cleanarchslvglass.domain.repository.SettingsRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class SettingsRepositoryImpl(private val settingsStorage: SettingsStorage): SettingsRepository {

    private val settingsList = mutableListOf<Settings>()

    override fun getSettings(): Flow<List<Settings>> {
        val settingsModel = settingsStorage.getSettings()
        val flowSettings = callbackFlow {
            settingsModel.collect{ settingsModelList ->
                settingsModelList.onEach {
                    val settings = Settings(
                        id = it.id,
                        notificationState = it.notificationState
                    )
                    settingsList.clear()
                    settingsList.add(settings)
                }
                this@callbackFlow.trySendBlocking(settingsList)
            }
            awaitClose {

            }
        }
        return flowSettings
    }

    override suspend fun insert(settings: Settings) {
        val settingsModel = SettingsModel(
            id = settings.id,
            notificationState = settings.notificationState
        )
        settingsStorage.insert(settingsModel)
    }

    override suspend fun updateNotificationState(state: Int) {
        settingsStorage.updateNotificationState(state = state)
    }
}