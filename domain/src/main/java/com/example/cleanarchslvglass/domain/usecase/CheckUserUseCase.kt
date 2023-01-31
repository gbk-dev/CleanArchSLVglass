package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.UserAuthRepository

class CheckUserUseCase(private val userAuthRepository: UserAuthRepository) {

    fun checkUser(): Boolean{
        return userAuthRepository.checkUser()
    }

}