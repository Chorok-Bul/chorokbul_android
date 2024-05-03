package com.chorokbul.data.repository.impl

import com.chorokbul.data.repository.GetAppPrefsRepository
import com.chorokbul.datastore.AppPrefsDataSource
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAppPrefsRepositoryImpl @Inject constructor(
    private val appPrefsDataSource: AppPrefsDataSource
) : GetAppPrefsRepository {
    override fun getIsSoundSignalDownloaded(): Flow<Boolean> {
        return appPrefsDataSource.isSoundSignalDownloaded()
    }
}
