package com.example.cleanarchslvglass.presentation.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchslvglass.data.db.RoomDataBase
import com.example.cleanarchslvglass.data.repository.SettingsRepositoryImpl
import com.example.cleanarchslvglass.data.storage.SharedPrefSettingsStorage
import com.example.cleanarchslvglass.domain.models.Basket
import com.example.cleanarchslvglass.domain.models.Settings
import com.example.cleanarchslvglass.domain.usecase.GetNotificationStateUseCase
import com.example.cleanarchslvglass.domain.usecase.SetSettingsUseCase
import com.example.cleanarchslvglass.domain.usecase.UpdateNotificationStateUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class SettingsViewModel(
    application : Application
) : AndroidViewModel(application)  {

    private var settingsRepository: SettingsRepositoryImpl

    init {
        val settingsDao = RoomDataBase.getDatabase(application).settingsDao()
        settingsRepository = SettingsRepositoryImpl(settingsStorage = SharedPrefSettingsStorage(settingsDao = settingsDao))
    }

    private val getNotificationStateUseCase by lazy {
        GetNotificationStateUseCase(settingsRepository = settingsRepository)
    }

    private val updateNotificationStateUseCase by lazy {
        UpdateNotificationStateUseCase(settingsRepository = settingsRepository)
    }

    private val setSettingsUseCase by lazy {
        SetSettingsUseCase(settingsRepository = settingsRepository)
    }

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