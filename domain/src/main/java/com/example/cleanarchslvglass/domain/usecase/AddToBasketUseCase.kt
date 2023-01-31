package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.repository.BasketRepository

class AddToBasketUseCase(private val basketRepository: BasketRepository) {

    suspend fun insert(basket: Basket){
        basketRepository.insert(basket)
    }

}