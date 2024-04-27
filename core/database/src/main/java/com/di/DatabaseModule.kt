package com.di

import android.content.Context
import androidx.room.Room
import com.common.Constants.CHOROK_BUL_DATABASE_NAME
import com.dao.RawDataDao
import com.database.ChorokBulDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): ChorokBulDatabase {
        return Room.databaseBuilder(
            context, ChorokBulDatabase::class.java, CHOROK_BUL_DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideRawDataDao(db: ChorokBulDatabase): RawDataDao {
        return db.rawDataDao()
    }
}
