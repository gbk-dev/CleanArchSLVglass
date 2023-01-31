package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.CategoryRepository

class CheckLanguageCategoryUseCase(private val categoryRepository: CategoryRepository) {

    fun checkLanguage(language: String): String{
        return categoryRepository.checkLanguage(language)
    }

}