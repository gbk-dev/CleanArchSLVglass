package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class BasketViewModel @Inject constructor(
    private val getBasketUseCase: GetBasketUseCase,
    private val addToBasketUseCase: AddToBasketUseCase,
    private val deleteFromBasketUseCase: DeleteFromBasketUseCase,
    private val deleteAllFromBasketUseCase: DeleteAllFromBasketUseCase,
    private val updateCountBasketUseCase: UpdateCountBasketUseCase,
    private val postMessageUseCase: PostMessageUseCase
) : ViewModel() {

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