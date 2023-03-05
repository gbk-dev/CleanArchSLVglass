package com.example.cleanarchslvglass.domain.repository

import com.example.cleanarchslvglass.domain.models.Resource
import com.example.cleanarchslvglass.domain.models.User
import kotlinx.coroutines.flow.Flow

interface UserAuthRepository {

    suspend fun createUser(user: User, password: String): Flow<Resource<Boolean>>

    suspend fun authUser(user: User, password: String): Flow<Resource<Boolean>>

    fun checkUser(): Boolean
}