package com.example.cleanarchslvglass.data.storage

import com.example.cleanarchslvglass.data.models.UserModel
import kotlinx.coroutines.flow.Flow

interface UserStorage {

    fun getUserData() : Flow<Result<UserModel>>

    fun logOut()

    fun updateUser(user: Map<String, String>): String

}