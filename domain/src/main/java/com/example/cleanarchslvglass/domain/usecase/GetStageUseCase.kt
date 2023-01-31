package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Stage
import com.example.cleanarchslvglass.domain.repository.StageRepository
import kotlinx.coroutines.flow.Flow

class GetStageUseCase(private val stageRepository: StageRepository) {

    fun getStage(): Flow<Result<List<Stage>>>{
        return stageRepository.getStage()
    }

}