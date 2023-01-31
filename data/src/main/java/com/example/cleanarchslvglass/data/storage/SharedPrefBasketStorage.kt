package com.example.cleanarchslvglass.data.storage

import com.example.cleanarchslvglass.data.models.BasketModel
import kotlinx.coroutines.flow.Flow

class SharedPrefBasketStorage(private val basketDao: BasketDao) : BasketStorage {

    override fun getAll(): Flow<List<BasketModel>> {
        return basketDao.getAll()
    }

    override suspend fun insert(basketModel: BasketModel) {
        return basketDao.insert(basketModel)
    }

    override suspend fun delete(basketModel: BasketModel){
        return basketDao.delete(basketModel)
    }

    override suspend fun deleteAll() {
        basketDao.deleteAll()
    }

    override suspend fun updateCount(count: Int, id: Int) {
        basketDao.updateCount(count = count, id = id)
    }
}