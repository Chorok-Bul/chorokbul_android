package com.chorokbul.database.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.chorokbul.database.entity.SoundSignalEntity
import com.chorokbul.database.dao.RawDataDao

@Database(
    entities = [SoundSignalEntity::class],
    version = 1
)
abstract class ChorokBulDatabase : RoomDatabase() {
    abstract fun rawDataDao(): RawDataDao
}
