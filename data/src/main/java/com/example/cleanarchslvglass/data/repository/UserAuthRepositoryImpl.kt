package com.example.cleanarchslvglass.data.repository

import com.example.cleanarchslvglass.domain.models.User
import com.example.cleanarchslvglass.domain.repository.UserAuthRepository
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.tasks.await

class UserAuthRepositoryImpl : UserAuthRepository {

    private val dbAuth: FirebaseAuth = FirebaseAuth.getInstance()
    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()

    override suspend fun createUser(user: User, password: String): String{

        val job = dbAuth.createUserWithEmailAndPassword(user.email.toString(), password)
        var result = ""
        job.addOnCompleteListener {
            if (it.isSuccessful) {
                result = "Успешно"
                val uid = FirebaseAuth.getInstance().uid
                val setUser = User(
                    firstName = user.firstName,
                    lastName = user.lastName,
                    email = user.email,
                    uid = uid
                )
                db.getReference("/Users/$uid").setValue(setUser)
            } else {
                result = it.exception.toString()
            }
        }.await()

        return result
    }

    override suspend fun authUser(user: User, password: String): String{
        var result = ""
        val job = dbAuth.signInWithEmailAndPassword(user.email.toString(), password)
        job.addOnCompleteListener {
            if (it.isSuccessful) {
                result = "Успешно"
            } else {
                result = it.exception.toString()
            }
        }.await()

        return result
    }

    override fun checkUser(): Boolean {
        return dbAuth.currentUser != null
    }
}