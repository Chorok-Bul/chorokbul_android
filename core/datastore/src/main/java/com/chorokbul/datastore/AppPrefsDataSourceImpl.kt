package com.chorokbul.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.intPreferencesKey
import com.chorokbul.datastore.extensions.editPrefs
import com.chorokbul.datastore.extensions.getPrefs
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AppPrefsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppPrefsDataSource {

    override suspend fun updateLastDBVersion(version: Int) {
        dataStore.editPrefs(
            key = PrefsKeys.DB_VERSION,
            value = version
        )
    }

    override fun getLastDBVersion(): Flow<Int> {
        return dataStore.getPrefs(
            key = PrefsKeys.DB_VERSION,
            defaultValue = 1
        )
    }

    override suspend fun updateSoundSignalDownloadedState(complete: Boolean) {
        dataStore.editPrefs(
            PrefsKeys.SOUND_SIGNAL_DATA_DOWNLOADED,
            value = complete
        )
    }

    override fun isSoundSignalDownloaded(): Flow<Boolean> {
        return dataStore.getPrefs(
            key = PrefsKeys.SOUND_SIGNAL_DATA_DOWNLOADED,
            defaultValue = false
        )
    }

    private object PrefsKeys {
        val SOUND_SIGNAL_DATA_DOWNLOADED = booleanPreferencesKey("sound_signal_data")
        val DB_VERSION = intPreferencesKey("db_version")
    }
}
