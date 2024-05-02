package com.chorokbul.data.repository.impl

import com.chorokbul.data.GetRawDataRepository
import com.chorokbul.database.entity.SoundSignalEntity
import com.chorokbul.database.dao.RawDataDao
import com.chorokbul.database.entity.asExternalModel
import com.chorokbul.model.SoundSignal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class GetRawDataRepositoryImpl @Inject constructor(
    private val dao: RawDataDao,
) : GetRawDataRepository {
    override suspend fun findLocationWithInBounds(
        leftLat: Double,
        leftLng: Double,
        rightLat: Double,
        rightLng: Double,
    ): Flow<List<SoundSignal>> {
        return dao.findLocationsWithinBounds(leftLat, rightLat, leftLng, rightLng)
            .map {
                it.map(SoundSignalEntity::asExternalModel)
            }
    }
}
