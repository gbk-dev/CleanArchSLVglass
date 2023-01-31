package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.StageRepository

class CheckLanguageStageUseCase(private val stageRepository: StageRepository) {

    fun checkLanguage(language: String) : String{
        return stageRepository.checkLanguage(language)
    }

}