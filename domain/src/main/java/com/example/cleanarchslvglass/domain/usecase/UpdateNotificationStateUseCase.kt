package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.SettingsRepository

class UpdateNotificationStateUseCase(private val settingsRepository: SettingsRepository) {

    suspend fun updateNotificationState(state: Int){
        settingsRepository.updateNotificationState(state = state)
    }

}