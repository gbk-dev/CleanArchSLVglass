package com.example.cleanarchslvglass.domain.repository

import com.example.cleanarchslvglass.domain.models.Glass
import com.example.cleanarchslvglass.domain.models.GlassContainer
import com.example.cleanarchslvglass.domain.models.Mirror
import kotlinx.coroutines.flow.Flow

interface ProductsRepository {

    fun getGlass() : Flow<Result<List<Glass>>>

    fun getMirror() : Flow<Result<Mirror>>

    fun getGlassCon() : Flow<Result<List<GlassContainer>>>

    fun checkLanguage(language: String) : String

}