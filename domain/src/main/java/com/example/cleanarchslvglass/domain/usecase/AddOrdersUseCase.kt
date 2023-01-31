package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Orders
import com.example.cleanarchslvglass.domain.repository.OrdersRepository

class AddOrdersUseCase(private val ordersRepository: OrdersRepository) {

    suspend fun insertOrders(orders: Orders){
        return ordersRepository.insertOrders(orders = orders)
    }

}