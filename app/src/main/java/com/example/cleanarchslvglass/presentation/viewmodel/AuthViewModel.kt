package com.example.cleanarchslvglass.presentation.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cleanarchslvglass.data.repository.UserAuthRepositoryImpl
import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.usecase.AuthUserUseCase
import com.example.cleanarchslvglass.domain.usecase.CheckUserUseCase
import com.example.cleanarchslvglass.domain.usecase.SetUserUseCase

class AuthViewModel : ViewModel() {

    private val userAuthRepository by lazy {
        UserAuthRepositoryImpl()
    }

    private val setUserUseCase by lazy {
        SetUserUseCase(userAuthRepository = userAuthRepository)
    }

    private val authUserUseCase by lazy {
        AuthUserUseCase(userAuthRepository = userAuthRepository)
    }

    private val checkUserUseCase by lazy {
        CheckUserUseCase(userAuthRepository = userAuthRepository)
    }

    suspend fun createUser(user: User, password: String): String{
        return setUserUseCase.createUser(user, password)
    }

    suspend fun authUser(user: User, password: String): String{
        return authUserUseCase.authUser(user, password)
    }

    fun checkUser(): Boolean{
        return checkUserUseCase.checkUser()
    }

}