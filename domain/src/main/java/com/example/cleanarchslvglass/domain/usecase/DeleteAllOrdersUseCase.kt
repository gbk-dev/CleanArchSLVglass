package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.OrdersRepository

class DeleteAllOrdersUseCase(private val ordersRepository: OrdersRepository) {

    suspend fun deleteAllOrders(){
        return ordersRepository.deleteAllOrders()
    }

}