package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.repository.UserRepository

class UpdateUserDataUseCase(private val userRepository: UserRepository) {

    fun updateUser(user: Map<String, String>): String{
        return userRepository.updateUser(user)
    }

}