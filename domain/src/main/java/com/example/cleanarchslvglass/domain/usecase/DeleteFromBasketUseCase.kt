package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow

class DeleteFromBasketUseCase(private val basketRepository: BasketRepository) {

    suspend fun delete(basket: Basket) {
        basketRepository.delete(basket)
    }

}