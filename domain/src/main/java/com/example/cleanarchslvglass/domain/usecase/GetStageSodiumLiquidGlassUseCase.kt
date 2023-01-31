package com.example.cleanarchslvglass.domain.usecase

import com.example.cleanarchslvglass.domain.models.Stage
import com.example.cleanarchslvglass.domain.repository.StageRepository
import kotlinx.coroutines.flow.Flow

class GetStageSodiumLiquidGlassUseCase(private val stageRepository: StageRepository) {

    fun getStageSodiumLiquidGlass() : Flow<Result<List<Stage>>> {
        return stageRepository.getStageSodiumLiquidGlass()
    }

}