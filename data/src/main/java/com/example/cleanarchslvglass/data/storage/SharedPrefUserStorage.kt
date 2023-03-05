package com.example.cleanarchslvglass.data.storage

import com.example.cleanarchslvglass.data.models.UserModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class SharedPrefUserStorage : UserStorage {

    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val dbAuth: FirebaseAuth = FirebaseAuth.getInstance()

    private val currentUser = dbAuth.uid
    private val url = "https://cleanarchslvglass-default-rtdb.europe-west1.firebasedatabase.app/"
    private lateinit var listener: ValueEventListener

    override fun getUserData()= callbackFlow<Result<UserModel>> {

        withContext(Dispatchers.Default) {
            listener = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val user = snapshot.getValue(UserModel::class.java)
                    this@callbackFlow.trySendBlocking(Result.success(user!!))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }

            }

            db.getReferenceFromUrl(url)
                .child("Users/$currentUser")
                .addValueEventListener(listener)
        }

        awaitClose {
            db.getReferenceFromUrl(url)
                .child("Users/$currentUser")
                .removeEventListener(listener)
        }

    }

    override fun logOut() {
        return dbAuth.signOut()
    }

    override fun updateUser(user: Map<String, String>): String {
        db.getReferenceFromUrl(url)
            .child("Users/$currentUser")
            .updateChildren(user)

        return "Данные обновлены"
    }

}