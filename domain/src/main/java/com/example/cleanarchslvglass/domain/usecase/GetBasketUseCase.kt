package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.repository.BasketRepository
import kotlinx.coroutines.flow.Flow

class GetBasketUseCase(private val basketRepository: BasketRepository) {

    fun getAll() : Flow<List<Basket>>{
        return basketRepository.getAll()
    }

}