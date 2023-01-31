package com.example.cleanarchslvglass.data.repository


import com.example.cleanarchslvglass.domain.models.Stage
import com.example.cleanarchslvglass.domain.repository.StageRepository
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.coroutines.async
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.channels.trySendBlocking
import kotlinx.coroutines.flow.callbackFlow

class StageRepositoryImpl : StageRepository {

    private val db: FirebaseDatabase = FirebaseDatabase.getInstance()
    private val url = "https://cleanarchslvglass-default-rtdb.europe-west1.firebasedatabase.app/"
    private var languageStage = ""
    private lateinit var listenerStageGlass: ValueEventListener
    private lateinit var listenerStageSodiumLiquidGlass: ValueEventListener
    private lateinit var listenerStageSodiumSilicate: ValueEventListener
    private lateinit var listenerStage: ValueEventListener

    override fun getStageGlass() = callbackFlow<Result<List<Stage>>> {

        async {

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

            if (checkLanguage(languageStage) == "ru"){
                db.getReferenceFromUrl(url)
                    .child("StageOfProduction/Glass")
                    .addValueEventListener(listenerStageGlass)
            } else {
                db.getReferenceFromUrl(url)
                    .child("StageOfProductionEn/Glass")
                    .addValueEventListener(listenerStageGlass)
            }

        }.await()

        awaitClose {
            db.getReferenceFromUrl(url)
                .child("StageOfProduction/Glass")
                .removeEventListener(listenerStageGlass)

            db.getReferenceFromUrl(url)
                .child("StageOfProductionEn/Glass")
                .removeEventListener(listenerStageGlass)
        }

    }

    override fun getStageSodiumLiquidGlass() = callbackFlow<Result<List<Stage>>> {

        async {

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

            if (checkLanguage(languageStage) == "ru"){
                db.getReferenceFromUrl(url)
                    .child("StageOfProduction/SodiumLiquidGlass")
                    .addValueEventListener(listenerStageSodiumLiquidGlass)
            } else {
                db.getReferenceFromUrl(url)
                    .child("StageOfProductionEn/SodiumLiquidGlass")
                    .addValueEventListener(listenerStageSodiumLiquidGlass)
            }

        }.await()

        awaitClose {
            db.getReferenceFromUrl(url)
                .child("StageOfProduction/SodiumLiquidGlass")
                .removeEventListener(listenerStageSodiumLiquidGlass)

            db.getReferenceFromUrl(url)
                .child("StageOfProductionEn/SodiumLiquidGlass")
                .removeEventListener(listenerStageSodiumLiquidGlass)
        }

    }

    override fun getStageSodiumSilicate() = callbackFlow<Result<List<Stage>>> {

        async {

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

            if (checkLanguage(languageStage) == "ru"){
                db.getReferenceFromUrl(url)
                    .child("StageOfProduction/SodiumSilicate")
                    .addValueEventListener(listenerStageSodiumSilicate)
            } else {
                db.getReferenceFromUrl(url)
                    .child("StageOfProductionEn/SodiumSilicate")
                    .addValueEventListener(listenerStageSodiumSilicate)
            }

        }.await()

        awaitClose {
            db.getReferenceFromUrl(url)
                .child("StageOfProduction/SodiumSilicate")
                .removeEventListener(listenerStageSodiumSilicate)

            db.getReferenceFromUrl(url)
                .child("StageOfProductionEn/SodiumSilicate")
                .removeEventListener(listenerStageSodiumSilicate)
        }

    }

    override fun getStage() = callbackFlow<Result<List<Stage>>> {

        async {

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

            if (checkLanguage(languageStage) == "ru"){
                db.getReferenceFromUrl(url)
                    .child("Stage")
                    .addValueEventListener(listenerStage)
            } else {
                db.getReferenceFromUrl(url)
                    .child("StageEn")
                    .addValueEventListener(listenerStage)
            }

        }.await()

        awaitClose {
            db.getReferenceFromUrl(url)
                .child("Stage")
                .removeEventListener(listenerStage)

            db.getReferenceFromUrl(url)
                .child("StageEn")
                .removeEventListener(listenerStage)
        }

    }

    override fun checkLanguage(language: String): String {
        languageStage = language
        return language
    }

}