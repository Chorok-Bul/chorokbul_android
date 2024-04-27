package com.database

import SoundSignal
import androidx.room.Database
import androidx.room.RoomDatabase
import com.dao.RawDataDao

@Database(
    version = 1
)
abstract class ChorokBulDatabase : RoomDatabase() {
}
