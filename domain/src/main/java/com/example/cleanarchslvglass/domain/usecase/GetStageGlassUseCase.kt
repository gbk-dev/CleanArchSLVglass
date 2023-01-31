package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Stage
import com.example.cleanarchslvglass.domain.repository.StageRepository
import kotlinx.coroutines.flow.Flow

class GetStageGlassUseCase(private val stageRepository: StageRepository) {

    fun getStageGlass() : Flow<Result<List<Stage>>> {
        return stageRepository.getStageGlass()
    }

}