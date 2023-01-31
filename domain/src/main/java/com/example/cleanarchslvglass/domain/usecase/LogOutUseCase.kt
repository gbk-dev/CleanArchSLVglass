package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.UserRepository

class LogOutUseCase(private val userRepository: UserRepository) {

    fun logOut() : Unit{
        return userRepository.logOut()
    }

}