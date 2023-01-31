package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Glass
import com.example.cleanarchslvglass.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetGlassUseCase(private val productsRepository: ProductsRepository) {

    fun getGlass() : Flow<Result<List<Glass>>>{
        return productsRepository.getGlass()
    }

}