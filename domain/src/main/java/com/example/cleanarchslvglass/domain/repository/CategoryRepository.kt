package com.example.cleanarchslvglass.domain.repository

import com.example.cleanarchslvglass.domain.models.Category
import kotlinx.coroutines.flow.Flow
import org.intellij.lang.annotations.Language

interface CategoryRepository {

    fun getCategory() : Flow<Result<List<Category>>>

    fun checkLanguage(language: String) : String

}