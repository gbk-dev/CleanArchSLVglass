package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.domain.models.Glass
import com.example.cleanarchslvglass.domain.usecase.CheckLanguageProductsUseCase
import com.example.cleanarchslvglass.domain.usecase.GetGlassUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GlassViewModel @Inject constructor(
    private val getGlassUseCase: GetGlassUseCase,
    private val checkLanguageProductsUseCase: CheckLanguageProductsUseCase
) : ViewModel() {

    private val _glassList = MutableLiveData<List<Glass>>()
    val glassList : LiveData<List<Glass>> by lazy {
        _glassList
    }

    fun getGlass(){
        viewModelScope.launch(Dispatchers.IO) {
            getGlassUseCase.getGlass().collect{
                when {
                    it.isSuccess -> {
                        val getGlassList = it.getOrNull()
                        _glassList.postValue(getGlassList!!)
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