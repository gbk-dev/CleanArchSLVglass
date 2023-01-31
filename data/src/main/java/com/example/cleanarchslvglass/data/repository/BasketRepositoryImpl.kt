package com.example.cleanarchslvglass.data.repository

import com.example.cleanarchslvglass.data.models.BasketModel
import com.example.cleanarchslvglass.data.service.BasketInstance
import com.example.cleanarchslvglass.data.storage.BasketStorage
import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.repository.BasketRepository
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

class BasketRepositoryImpl(private val basketStorage: BasketStorage) : BasketRepository {

    private val mutBasketList = mutableListOf<Basket>()

    override fun getAll() : Flow<List<Basket>>{
        val basketModel = basketStorage.getAll()
        val flowBasket = callbackFlow {
            basketModel.collect { basketModelList ->
                var i = 0
                mutBasketList.clear()
                while (i < basketModelList.size){
                    val basket = Basket(
                        id = basketModelList[i].id,
                        article = basketModelList[i].article,
                        title = basketModelList[i].title,
                        capacity = basketModelList[i].capacity,
                        collarType = basketModelList[i].collarType,
                        count = basketModelList[i].count,
                        img = basketModelList[i].img,
                        userId = basketModelList[i].userId
                    )
                    i++

                    mutBasketList.add(basket)
                    this@callbackFlow.trySendBlocking(mutBasketList)
                }
            }
            awaitClose {}
        }
        return flowBasket
    }

    override suspend fun insert(basket: Basket) {
        val basketModel = BasketModel(
            id = basket.id,
            article = basket.article,
            title = basket.title,
            capacity = basket.capacity,
            collarType = basket.collarType,
            count = basket.count,
            img = basket.img,
            userId = basket.userId
        )
        basketStorage.insert(basketModel)
    }

    override suspend fun delete(basket: Basket){
        val basketModel = BasketModel(
            id = basket.id,
            article = basket.article,
            title = basket.title,
            capacity = basket.capacity,
            collarType = basket.collarType,
            count = basket.count,
            img = basket.img,
            userId = basket.userId
        )
        mutBasketList.remove(basket)
        basketStorage.delete(basketModel)
    }

    override suspend fun deleteAll() {
        basketStorage.deleteAll()
    }

    override suspend fun updateCount(count: Int, id: Int) {
        basketStorage.updateCount(count = count, id = id)
    }

    override suspend fun postMessage(message: String){
        BasketInstance.api.sendReq(message = message)
    }
}