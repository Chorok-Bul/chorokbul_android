package com.chorokbul.data.di

import com.chorokbul.data.repository.GetAppPrefsRepository
import com.chorokbul.data.repository.InsertRawDataRepository
import com.chorokbul.data.repository.impl.GetAppPrefsRepositoryImpl
import com.chorokbul.data.repository.impl.InsertRawDataRepositoryImpl
import com.chorokbul.datastore.AppPrefsDataSource
import com.chorokbul.datastore.AppPrefsDataSourceImpl
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

    @Binds
    abstract fun bindsAppPrefsDataSource(
        impl: AppPrefsDataSourceImpl
    ): AppPrefsDataSource

    @Binds
    abstract fun bindsGetAppPrefsRepository(
        impl: GetAppPrefsRepositoryImpl
    ): GetAppPrefsRepository
}
