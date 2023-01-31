package com.example.cleanarchslvglass.data.storage

import com.example.cleanarchslvglass.data.models.OrdersModel
import kotlinx.coroutines.flow.Flow

class SharedPrefOrdersStorage(private val ordersDao: OrdersDao) : OrdersStorage {

    override fun getAllOrders(): Flow<List<OrdersModel>> {
        return ordersDao.getAllOrders()
    }

    override suspend fun insertOrders(ordersModel: OrdersModel) {
        return ordersDao.insertOrders(ordersModel)
    }

    override suspend fun deleteAllOrders() {
        return ordersDao.deleteAllOrders()
    }

}