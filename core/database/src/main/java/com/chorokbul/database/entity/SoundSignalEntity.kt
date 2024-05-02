package com.chorokbul.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.chorokbul.model.SoundSignal

/**
 * 음향신호기
 *
 * @property id
 * @property type 데이터 구분(음향신호기 데이터 type == 1)
 * @property lat 위도
 * @property lng 경도
 */
@Entity(tableName = "soundSignal")
data class SoundSignalEntity(
    @PrimaryKey
    val id: Int = 0,
    val type: Int = 1,
    val lat: Double,
    val lng: Double,
)

fun SoundSignalEntity.asExternalModel() = SoundSignal(
    id = id,
    isAcousticSound = type == 1,
    lat = lat,
    lng = lng,
)
