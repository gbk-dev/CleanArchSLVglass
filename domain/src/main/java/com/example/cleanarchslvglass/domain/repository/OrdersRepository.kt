package com.example.cleanarchslvglass.domain.repository

import com.example.cleanarchslvglass.domain.models.Orders
import kotlinx.coroutines.flow.Flow

interface OrdersRepository {

    fun getAllOrders() : Flow<List<Orders>>

    suspend fun insertOrders(orders: Orders)

    suspend fun deleteAllOrders()

}