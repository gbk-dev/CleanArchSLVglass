package com.example.cleanarchslvglass.domain.repository

import com.example.cleanarchslvglass.domain.models.Stage
import kotlinx.coroutines.flow.Flow

interface StageRepository {

    fun getStageGlass(): Flow<Result<List<Stage>>>

    fun getStageSodiumLiquidGlass(): Flow<Result<List<Stage>>>

    fun getStageSodiumSilicate(): Flow<Result<List<Stage>>>

    fun getStage(): Flow<Result<List<Stage>>>

    fun checkLanguage(language: String) : String

}