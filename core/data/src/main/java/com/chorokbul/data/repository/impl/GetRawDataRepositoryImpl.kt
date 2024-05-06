package com.chorokbul.data.repository.impl

import com.chorokbul.data.GetRawDataRepository
import com.chorokbul.data.model.DeltaLatLng
import com.chorokbul.database.entity.SoundSignalEntity
import com.chorokbul.database.dao.RawDataDao
import com.chorokbul.database.entity.asExternalModel
import com.chorokbul.model.SoundSignal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.math.cos

internal class GetRawDataRepositoryImpl @Inject constructor(
    private val dao: RawDataDao,
) : GetRawDataRepository {
    override suspend fun getSoundSignalListWithInBounds(
        latitude: Double,
        longitude: Double,
    ): Flow<List<SoundSignal>> {
        val deltaLatLng = getDeltaLatLng(latitude, longitude)

        return dao.getSoundSignalListWithInBounds(
            deltaLatLng.leftLat,
            deltaLatLng.rightLat,
            deltaLatLng.leftLng,
            deltaLatLng.rightLng
        )
            .map {
                it.map(SoundSignalEntity::asExternalModel)
            }
    }

    private fun getDeltaLatLng(lat: Double, lng: Double): DeltaLatLng {
        val distance = 1.0 // km 단위
        val approximate = 111.32

        val deltaLat = distance / approximate // 위도 변화량
        val deltaLng = distance / (approximate * cos(Math.toRadians(lat))) // 경도 변화량
        return DeltaLatLng(
            leftLat = lat - deltaLat,
            rightLat = lat + deltaLat,
            leftLng = lng - deltaLng,
            rightLng = lng + deltaLng,
        )
    }
}
