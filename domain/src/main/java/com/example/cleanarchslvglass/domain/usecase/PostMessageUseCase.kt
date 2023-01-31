package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.BasketRepository

class PostMessageUseCase(private val basketRepository: BasketRepository) {

    suspend fun postMessage(message: String){
        basketRepository.postMessage(message = message)
    }

}