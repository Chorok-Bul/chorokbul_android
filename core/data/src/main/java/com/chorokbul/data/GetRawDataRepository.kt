package com.chorokbul.data

import com.chorokbul.model.SoundSignal
import kotlinx.coroutines.flow.Flow

interface GetRawDataRepository {
    suspend fun getSoundSignalListWithInBounds(
        latitude: Double,
        longitude: Double,
    ): Flow<List<SoundSignal>>
}
