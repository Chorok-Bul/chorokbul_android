package com.chorokbul.datastore

import com.chorokbul.datastore.model.AppPrefs
import kotlinx.coroutines.flow.Flow

/**
 * 앱의 Preferences를 관리하는 DataStore<Preferences>의 DataSource.
 *
 */
interface AppPrefsDataSource {
    suspend fun updateAppPrefs(appPrefs: AppPrefs)
    fun getAppPrefs(): Flow<AppPrefs>
}
