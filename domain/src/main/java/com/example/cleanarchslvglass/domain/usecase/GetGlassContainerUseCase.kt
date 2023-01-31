package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.GlassContainer
import com.example.cleanarchslvglass.domain.repository.ProductsRepository
import kotlinx.coroutines.flow.Flow

class GetGlassContainerUseCase(private val productsRepository: ProductsRepository) {

    fun getGlassCon() : Flow<Result<List<GlassContainer>>> {
        return productsRepository.getGlassCon()
    }

}