package com.example.cleanarchslvglass.data.repository

import com.example.cleanarchslvglass.data.models.OrdersModel
import com.example.cleanarchslvglass.data.storage.OrdersStorage
import com.example.cleanarchslvglass.domain.models.Orders
import com.example.cleanarchslvglass.domain.repository.OrdersRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class OrdersRepositoryImpl(private val ordersStorage: OrdersStorage) : OrdersRepository {

    private val ordersList = mutableListOf<Orders>()

    override fun getAllOrders(): Flow<List<Orders>> {
        val ordersModel = ordersStorage.getAllOrders()
        val flowOrders = callbackFlow {
            ordersModel.collect { ordersModelList ->
                ordersModelList.onEach {
                    val orders = Orders(
                        id = it.id,
                        article = it.article,
                        title = it.title,
                        capacity = it.capacity,
                        collarType = it.collarType,
                        count = it.count,
                        img = it.img,
                        userId = it.userId
                    )
                    ordersList.add(orders)
                }
                this@callbackFlow.trySendBlocking(ordersList)
            }
            awaitClose {

            }
        }
        return flowOrders
    }

    override suspend fun insertOrders(orders: Orders) {
        val ordersModel = OrdersModel(
            id = orders.id,
            article = orders.article,
            title = orders.title,
            capacity = orders.capacity,
            collarType = orders.collarType,
            count = orders.count,
            img = orders.img,
            userId = orders.userId
        )

        ordersStorage.insertOrders(ordersModel = ordersModel)
    }

    override suspend fun deleteAllOrders() {
        ordersStorage.deleteAllOrders()
    }
}