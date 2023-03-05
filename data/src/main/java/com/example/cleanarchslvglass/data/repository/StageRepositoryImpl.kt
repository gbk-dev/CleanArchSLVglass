package com.example.cleanarchslvglass.data.repository


import com.example.cleanarchslvglass.domain.models.Stage
import com.example.cleanarchslvglass.domain.repository.StageRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.withContext

class StageRepositoryImpl : StageRepository {

    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private var languageStage = ""
    private lateinit var listenerStageGlass: ValueEventListener
    private lateinit var listenerStageSodiumLiquidGlass: ValueEventListener
    private lateinit var listenerStageSodiumSilicate: ValueEventListener
    private lateinit var listenerStage: ValueEventListener

    override fun getStageGlass() = callbackFlow<Result<List<Stage>>> {

        withContext(Dispatchers.Default) {

            listenerStageGlass = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val glass = snapshot.children.map { ds ->
                        ds.getValue(Stage::class.java)
                    }
                    this@callbackFlow.trySendBlocking(Result.success(glass.filterNotNull()))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }
            }

            if (checkLanguage(languageStage) == "ru") {
                db.reference
                    .child("StageOfProduction/Glass")
                    .addValueEventListener(listenerStageGlass)
            } else {
                db.reference
                    .child("StageOfProductionEn/Glass")
                    .addValueEventListener(listenerStageGlass)
            }

        }

        awaitClose {
            db.reference
                .child("StageOfProduction/Glass")
                .removeEventListener(listenerStageGlass)

            db.reference
                .child("StageOfProductionEn/Glass")
                .removeEventListener(listenerStageGlass)
        }

    }

    override fun getStageSodiumLiquidGlass() = callbackFlow<Result<List<Stage>>> {

        withContext(Dispatchers.Default) {

            listenerStageSodiumLiquidGlass = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val sodiumLiquidGlass = snapshot.children.map { ds ->
                        ds.getValue(Stage::class.java)
                    }
                    this@callbackFlow.trySendBlocking(Result.success(sodiumLiquidGlass.filterNotNull()))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }
            }

            if (checkLanguage(languageStage) == "ru") {
                db.reference
                    .child("StageOfProduction/SodiumLiquidGlass")
                    .addValueEventListener(listenerStageSodiumLiquidGlass)
            } else {
                db.reference
                    .child("StageOfProductionEn/SodiumLiquidGlass")
                    .addValueEventListener(listenerStageSodiumLiquidGlass)
            }

        }

        awaitClose {
            db.reference
                .child("StageOfProduction/SodiumLiquidGlass")
                .removeEventListener(listenerStageSodiumLiquidGlass)

            db.reference
                .child("StageOfProductionEn/SodiumLiquidGlass")
                .removeEventListener(listenerStageSodiumLiquidGlass)
        }

    }

    override fun getStageSodiumSilicate() = callbackFlow<Result<List<Stage>>> {

        withContext(Dispatchers.Default) {

            listenerStageSodiumSilicate = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val sodiumSilicate = snapshot.children.map { ds ->
                        ds.getValue(Stage::class.java)
                    }
                    this@callbackFlow.trySendBlocking(Result.success(sodiumSilicate.filterNotNull()))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }
            }

            if (checkLanguage(languageStage) == "ru") {
                db.reference
                    .child("StageOfProduction/SodiumSilicate")
                    .addValueEventListener(listenerStageSodiumSilicate)
            } else {
                db.reference
                    .child("StageOfProductionEn/SodiumSilicate")
                    .addValueEventListener(listenerStageSodiumSilicate)
            }

        }

        awaitClose {
            db.reference
                .child("StageOfProduction/SodiumSilicate")
                .removeEventListener(listenerStageSodiumSilicate)

            db.reference
                .child("StageOfProductionEn/SodiumSilicate")
                .removeEventListener(listenerStageSodiumSilicate)
        }

    }

    override fun getStage() = callbackFlow<Result<List<Stage>>> {

        withContext(Dispatchers.Default) {

            listenerStage = object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val stage = snapshot.children.map { ds ->
                        ds.getValue(Stage::class.java)
                    }
                    this@callbackFlow.trySendBlocking(Result.success(stage.filterNotNull()))
                }

                override fun onCancelled(error: DatabaseError) {
                    this@callbackFlow.trySendBlocking(Result.failure(error.toException()))
                }
            }

            if (checkLanguage(languageStage) == "ru") {
                db.reference
                    .child("Stage")
                    .addValueEventListener(listenerStage)
            } else {
                db.reference
                    .child("StageEn")
                    .addValueEventListener(listenerStage)
            }

        }

        awaitClose {
            db.reference
                .child("Stage")
                .removeEventListener(listenerStage)

            db.reference
                .child("StageEn")
                .removeEventListener(listenerStage)
        }

    }

    override fun checkLanguage(language: String): String {
        languageStage = language
        return language
    }

}