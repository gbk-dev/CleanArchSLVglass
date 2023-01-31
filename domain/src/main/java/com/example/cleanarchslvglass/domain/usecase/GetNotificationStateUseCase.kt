package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Settings
import com.example.cleanarchslvglass.domain.repository.SettingsRepository
import kotlinx.coroutines.flow.Flow

class GetNotificationStateUseCase(private val settingsRepository: SettingsRepository) {

    fun getSettings(): Flow<List<Settings>> {
        return settingsRepository.getSettings()
    }

}