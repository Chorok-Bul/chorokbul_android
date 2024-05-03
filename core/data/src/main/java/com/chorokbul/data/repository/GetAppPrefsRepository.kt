package com.chorokbul.data.repository

import kotlinx.coroutines.flow.Flow

interface GetAppPrefsRepository {
    fun getIsSoundSignalDownloaded(): Flow<Boolean>
}
