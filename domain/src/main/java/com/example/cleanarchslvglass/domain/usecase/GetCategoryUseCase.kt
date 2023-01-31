package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Category
import com.example.cleanarchslvglass.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow

class GetCategoryUseCase(private val categoryRepository: CategoryRepository) {

    fun getCategory() : Flow<Result<List<Category>>> {
        return categoryRepository.getCategory()
    }

}