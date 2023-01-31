package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.BasketRepository

class UpdateCountBasketUseCase(private val basketRepository: BasketRepository) {

    suspend fun updateCount(count: Int, id: Int){
        basketRepository.updateCount(count = count, id = id)
    }

}