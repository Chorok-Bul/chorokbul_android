package com.chorokbul.domain

import com.chorokbul.data.GetRawDataRepository
import com.chorokbul.model.SoundSignal
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class FindLocationWithInBoundsUseCase @Inject constructor(
    private val getRawDataRepository: GetRawDataRepository,
) {
    suspend operator fun invoke(
        leftLat: Double,
        leftLng: Double,
        rightLat: Double,
        rightLng: Double,
    ): Flow<List<SoundSignal>> {
        return getRawDataRepository.findLocationWithInBounds(leftLat, leftLng, rightLat, rightLng)
    }
}
