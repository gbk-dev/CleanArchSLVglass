package com.example.cleanarchslvglass.domain.repository


import kotlinx.coroutines.flow.Flow
import com.example.cleanarchslvglass.domain.models.Basket

interface BasketRepository {

    fun getAll() : Flow<List<Basket>>

    suspend fun insert(basket: Basket)

    suspend fun delete(basket: Basket)

    suspend fun deleteAll()

    suspend fun updateCount(count: Int, id: Int)

    suspend fun postMessage(message: String)

}