package com.chorokbul.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.chorokbul.database.entity.SoundSignalEntity
import kotlinx.coroutines.flow.Flow

/**
 * raw data를 database table에 insert하고 관리하는 역할을 수행 합니다.
 *
 */
@Dao
interface RawDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSoundSignalData(soundSignalList: List<SoundSignalEntity>)

    @Query(
        "SELECT * FROM soundSignal WHERE " +
                "lat BETWEEN :leftLat AND :rightLat " +
                "AND " +
                "lng BETWEEN :leftLng AND :rightLng"
    )
    fun getSoundSignalListWithInBounds(
        leftLat: Double,
        rightLat: Double,
        leftLng: Double,
        rightLng: Double,
    ): Flow<List<SoundSignalEntity>>
}
