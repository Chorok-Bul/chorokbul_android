package com.chorokbul.datastore

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import com.chorokbul.datastore.model.AppPrefs
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AppPrefsDataSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : AppPrefsDataSource {
    override suspend fun updateAppPrefs(appPrefs: AppPrefs) {
        dataStore.edit { prefs ->
            prefs[PrefsKeys.SOUND_SIGNAL_DATA_DOWNLOADED] = appPrefs.isSoundSignalDownloaded
            prefs[PrefsKeys.CROSSWALK_DATA_DOWNLOADED] = appPrefs.isCrosswalkDownloaded
            prefs[PrefsKeys.DB_VERSION] = appPrefs.dbVersion
        }
    }

    override fun getAppPrefs(): Flow<AppPrefs> {
        return dataStore.data.map { prefs ->
            AppPrefs(
                isSoundSignalDownloaded = prefs[PrefsKeys.SOUND_SIGNAL_DATA_DOWNLOADED] ?: false,
                isCrosswalkDownloaded = prefs[PrefsKeys.CROSSWALK_DATA_DOWNLOADED] ?: false,
                dbVersion = prefs[PrefsKeys.DB_VERSION] ?: 1
            )
        }
    }

    private object PrefsKeys {
        val SOUND_SIGNAL_DATA_DOWNLOADED = booleanPreferencesKey("sound_signal_data")
        val CROSSWALK_DATA_DOWNLOADED = booleanPreferencesKey("crosswalk_data")
        val DB_VERSION = intPreferencesKey("db_version")
    }
}
