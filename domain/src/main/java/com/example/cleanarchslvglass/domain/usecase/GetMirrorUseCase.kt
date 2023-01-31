package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Mirror
import com.example.cleanarchslvglass.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetMirrorUseCase(private val productsRepository: ProductsRepository) {

    fun getMirror() : Flow<Result<Mirror>> {
        return productsRepository.getMirror()
    }

}