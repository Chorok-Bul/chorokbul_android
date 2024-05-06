package com.chorokbul.domain

import com.chorokbul.data.GetRawDataRepository
import com.chorokbul.model.SoundSignal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSoundSignalListWithInBoundsUseCase @Inject constructor(
    private val getRawDataRepository: GetRawDataRepository,
) {
    suspend operator fun invoke(
        latitude: Double,
        longitude: Double,
    ): Flow<List<SoundSignal>> {
        return getRawDataRepository.getSoundSignalListWithInBounds(latitude, longitude)
    }
}
