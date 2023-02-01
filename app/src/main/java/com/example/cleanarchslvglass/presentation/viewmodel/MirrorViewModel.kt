package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.domain.models.Mirror
import com.example.cleanarchslvglass.domain.usecase.CheckLanguageProductsUseCase
import com.example.cleanarchslvglass.domain.usecase.GetMirrorUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MirrorViewModel @Inject constructor(
    private val getMirrorUseCase: GetMirrorUseCase,
    private val checkLanguageProductsUseCase: CheckLanguageProductsUseCase
) : ViewModel() {

    private val _mirror = MutableLiveData<Mirror>()
    val mirror : LiveData<Mirror> by lazy {
        _mirror
    }

    fun getMirror(){
        viewModelScope.launch(Dispatchers.IO) {
            getMirrorUseCase.getMirror().collect{
                when {
                    it.isSuccess -> {
                        val getMirrorModel = it.getOrNull()
                        _mirror.postValue(getMirrorModel!!)
                    }

                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun checkLanguage(language: String): String{
        return checkLanguageProductsUseCase.checkLanguage(language)
    }
}