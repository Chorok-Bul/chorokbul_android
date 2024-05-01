package com.chorokbul.data.di

import com.chorokbul.data.repository.InsertRawDataRepository
import com.chorokbul.data.repository.impl.InsertRawDataRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
internal abstract class DataModule {
    @Binds
    abstract fun bindsInsertRawDataRepository(
        impl: InsertRawDataRepositoryImpl
    ): InsertRawDataRepository
}
