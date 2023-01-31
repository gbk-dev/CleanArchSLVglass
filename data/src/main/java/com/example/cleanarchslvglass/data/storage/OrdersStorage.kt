package com.example.cleanarchslvglass.data.storage

import com.example.cleanarchslvglass.data.models.OrdersModel
import kotlinx.coroutines.flow.Flow

interface OrdersStorage {

    fun getAllOrders() : Flow<List<OrdersModel>>

    suspend fun insertOrders(ordersModel: OrdersModel)

    suspend fun deleteAllOrders()

}