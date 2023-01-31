package com.example.cleanarchslvglass.presentation.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.example.cleanarchslvglass.data.db.RoomDataBase
import com.example.cleanarchslvglass.data.repository.BasketRepositoryImpl
import com.example.cleanarchslvglass.data.storage.BasketDao
import com.example.cleanarchslvglass.data.storage.SharedPrefBasketStorage
import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.usecase.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class BasketViewModel(
    application : Application
) : AndroidViewModel(application) {

    private var basketRepository : BasketRepositoryImpl

    init {
        val basketDao = RoomDataBase.getDatabase(application).basketDao()
        basketRepository = BasketRepositoryImpl(basketStorage = SharedPrefBasketStorage(basketDao))
    }

    private val getBasketUseCase by lazy {
        GetBasketUseCase(basketRepository)
    }

    private val addToBasketUseCase by lazy {
        AddToBasketUseCase(basketRepository)
    }

    private val deleteFromBasketUseCase by lazy {
        DeleteFromBasketUseCase(basketRepository)
    }

    private val deleteAllFromBasketUseCase by lazy {
        DeleteAllFromBasketUseCase(basketRepository)
    }

    private val updateCountBasketUseCase by lazy {
        UpdateCountBasketUseCase(basketRepository)
    }

    private val postMessageUseCase by lazy {
        PostMessageUseCase(basketRepository)
    }

    private val _basketList = MutableLiveData<List<Basket>>()
    val basketList : LiveData<List<Basket>> by lazy {
        _basketList
    }

    fun getAll(){
        viewModelScope.launch(Dispatchers.IO) {
            getBasketUseCase.getAll().collect {
                _basketList.postValue(it)
            }
        }
    }

    fun insert(basket: Basket){
        viewModelScope.launch(Dispatchers.IO) {
            addToBasketUseCase.insert(basket)
        }
    }

    fun delete(basket: Basket) {
        viewModelScope.launch(Dispatchers.IO) {
            deleteFromBasketUseCase.delete(basket)
        }
    }

    fun deleteAll(){
        viewModelScope.launch(Dispatchers.IO) {
            deleteAllFromBasketUseCase.deleteAll()
        }
    }

    fun updateCount(count: Int, id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            updateCountBasketUseCase.updateCount(count = count, id = id)
        }
    }

    suspend fun postMessage(message: String){
        postMessageUseCase.postMessage(message = message)
    }
}