package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.BasketRepository

class DeleteAllFromBasketUseCase(private val basketRepository: BasketRepository) {

    suspend fun deleteAll(){
        basketRepository.deleteAll()
    }

}