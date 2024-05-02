package com.chorokbul.datastore

import kotlinx.coroutines.flow.Flow

/**
 * 앱의 Preferences를 관리하는 DataStore<Preferences>의 DataSource.
 *
 */
interface AppPrefsDataSource {
    // DB 버전 저장 및 읽기
    suspend fun updateLastDBVersion(version: Int)
    fun getLastDBVersion(): Flow<Int>

    // Sound Signal RawData Download 여부 저장 및 읽기
    suspend fun updateSoundSignalDownloadedState(complete: Boolean)
    fun isSoundSignalDownloaded(): Flow<Boolean>
}
