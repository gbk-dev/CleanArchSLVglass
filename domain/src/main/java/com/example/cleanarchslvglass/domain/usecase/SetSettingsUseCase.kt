package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Settings
import com.example.cleanarchslvglass.domain.repository.SettingsRepository

class SetSettingsUseCase(private val settingsRepository: SettingsRepository) {

    suspend fun insert(settings: Settings){
        settingsRepository.insert(settings)
    }

}