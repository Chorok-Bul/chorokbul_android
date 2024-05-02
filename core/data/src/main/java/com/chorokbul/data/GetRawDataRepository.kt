package com.chorokbul.data

import com.chorokbul.model.SoundSignal
import kotlinx.coroutines.flow.Flow

interface GetRawDataRepository {
    suspend fun findLocationWithInBounds(
        leftLat: Double,
        leftLng: Double,
        rightLat: Double,
        rightLng: Double,
    ): Flow<List<SoundSignal>>
}
