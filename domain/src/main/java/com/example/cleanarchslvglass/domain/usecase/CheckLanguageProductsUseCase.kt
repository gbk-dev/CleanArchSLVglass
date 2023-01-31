package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.ProductsRepository

class CheckLanguageProductsUseCase(private val productsRepository: ProductsRepository) {

    fun checkLanguage(language: String) : String{
        return productsRepository.checkLanguage(language)
    }

}