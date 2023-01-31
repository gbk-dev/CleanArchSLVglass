package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.domain.models.Settings
import com.example.cleanarchslvglass.domain.usecase.GetNotificationStateUseCase
import com.example.cleanarchslvglass.domain.usecase.SetSettingsUseCase
import com.example.cleanarchslvglass.domain.usecase.UpdateNotificationStateUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val getNotificationStateUseCase: GetNotificationStateUseCase,
    private val updateNotificationStateUseCase: UpdateNotificationStateUseCase,
    private val setSettingsUseCase: SetSettingsUseCase
) : ViewModel() {

    private val _settingsList = MutableLiveData<List<Settings>>()
    val settingsList : LiveData<List<Settings>> by lazy {
        _settingsList
    }

    fun getSettings(){
        viewModelScope.launch(Dispatchers.IO) {
            getNotificationStateUseCase.getSettings().collect{
                _settingsList.postValue(it)
            }
        }
    }

    fun insert(settings: Settings){
        viewModelScope.launch(Dispatchers.IO) {
            setSettingsUseCase.insert(settings)
        }
    }

    fun updateNotificationState(state: Int){
        viewModelScope.launch(Dispatchers.IO) {
            updateNotificationStateUseCase.updateNotificationState(state = state)
        }
    }
}