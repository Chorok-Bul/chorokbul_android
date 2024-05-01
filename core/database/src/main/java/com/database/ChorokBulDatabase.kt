package com.database

import SoundSignal
import androidx.room.Database
import androidx.room.RoomDatabase
import com.dao.RawDataDao

@Database(
    entities = [SoundSignal::class],
    version = 1
)
abstract class ChorokBulDatabase : RoomDatabase() {
    abstract fun rawDataDao(): RawDataDao
}
