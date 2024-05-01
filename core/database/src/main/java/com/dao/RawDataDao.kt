package com.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import com.chorokbul.model.SoundSignal

/**
 * raw data를 database table에 insert하고 관리하는 역할을 수행 합니다.
 *
 */
@Dao
interface RawDataDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSoundSignalData(soundSignalList: List<SoundSignal>)
}
