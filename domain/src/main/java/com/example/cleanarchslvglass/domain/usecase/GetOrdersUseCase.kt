package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Orders
import com.example.cleanarchslvglass.domain.repository.OrdersRepository
import kotlinx.coroutines.flow.Flow

class GetOrdersUseCase(private val ordersRepository: OrdersRepository) {

    fun getAllOrders(): Flow<List<Orders>> {
        return ordersRepository.getAllOrders()
    }

}