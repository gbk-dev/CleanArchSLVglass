package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.domain.models.Stage
import com.example.cleanarchslvglass.domain.usecase.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class StageViewModel @Inject constructor(
    private val getStageGlassUseCase: GetStageGlassUseCase,
    private val getStageSodiumLiquidGlassUseCase: GetStageSodiumLiquidGlassUseCase,
    private val getStageSodiumSilicateUseCase: GetStageSodiumSilicateUseCase,
    private val getStageUseCase: GetStageUseCase,
    private val checkLanguageStageUseCase: CheckLanguageStageUseCase
) : ViewModel() {

    private val _stageGlass = MutableLiveData<List<Stage>>()
    val stageGlass : LiveData<List<Stage>> by lazy {
        _stageGlass
    }

    private val _stageGlassSodiumLiquidGlass = MutableLiveData<List<Stage>>()
    val stageGlassSodiumLiquidGlass : LiveData<List<Stage>> by lazy {
        _stageGlassSodiumLiquidGlass
    }

    private val _stageGlassSodiumSilicate = MutableLiveData<List<Stage>>()
    val stageGlassSodiumSilicate : LiveData<List<Stage>> by lazy {
        _stageGlassSodiumSilicate
    }

    private val _stageList = MutableLiveData<List<Stage>>()
    val stageList : LiveData<List<Stage>> by lazy {
        _stageList
    }

    fun getStageGlass(){
        viewModelScope.launch(Dispatchers.IO) {
            getStageGlassUseCase.getStageGlass().collect{
                when {
                    it.isSuccess -> {
                        val getStageGlassList = it.getOrNull()
                        _stageGlass.postValue(getStageGlassList!!)
                    }
                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun getStageSodiumLiquidGlass(){
        viewModelScope.launch(Dispatchers.IO) {
            getStageSodiumLiquidGlassUseCase.getStageSodiumLiquidGlass().collect{
                when {
                    it.isSuccess -> {
                        val getStageSodiumLiquidGlassList = it.getOrNull()
                        _stageGlassSodiumLiquidGlass.postValue(getStageSodiumLiquidGlassList!!)
                    }

                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun getStageSodiumSilicate(){
        viewModelScope.launch(Dispatchers.IO) {
            getStageSodiumSilicateUseCase.getStageSodiumSilicate().collect{
                when {
                    it.isSuccess -> {
                        val getStageSodiumSilicateList = it.getOrNull()
                        _stageGlassSodiumSilicate.postValue(getStageSodiumSilicateList!!)
                    }

                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun getStage(){
        viewModelScope.launch(Dispatchers.IO) {
            getStageUseCase.getStage().collect{
                when {
                    it.isSuccess -> {
                        val getStageList = it.getOrNull()
                        _stageList.postValue(getStageList!!)
                    }

                    it.isFailure -> {
                        it.exceptionOrNull()?.printStackTrace()
                    }
                }
            }
        }
    }

    fun checkLanguage(language: String): String{
        return checkLanguageStageUseCase.checkLanguage(language)
    }
}