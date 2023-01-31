package com.example.cleanarchslvglass.data.storage

import com.example.cleanarchslvglass.data.models.BasketModel
import kotlinx.coroutines.flow.Flow

interface BasketStorage {

    fun getAll() : Flow<List<BasketModel>>

    suspend fun insert(basketModel: BasketModel)

    suspend fun delete(basketModel: BasketModel)

    suspend fun deleteAll()

    suspend fun updateCount(count: Int, id: Int)

}