package com.example.amphibians.data

import com.example.amphibians.model.Amphibian
import com.example.amphibians.network.AmphibiansApiService

interface AmphibiansDataRepository {
    suspend fun getAmphibiansData(): List<Amphibian>
}

class NetworkAmphibiansDataRepository(
    private val amphibiansDataService: AmphibiansApiService
) : AmphibiansDataRepository {
    override suspend fun getAmphibiansData(): List<Amphibian> = amphibiansDataService.getAmphibianData()
}