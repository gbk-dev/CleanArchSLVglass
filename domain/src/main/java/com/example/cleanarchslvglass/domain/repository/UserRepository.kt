package com.example.cleanarchslvglass.domain.repository

import com.example.cleanarchslvglass.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserRepository {

    fun getUserData() : Flow<Result<User>>

    fun logOut()

    fun updateUser(user: Map<String, String>): String
}