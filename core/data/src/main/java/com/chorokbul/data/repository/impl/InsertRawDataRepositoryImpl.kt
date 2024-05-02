package com.chorokbul.data.repository.impl

import android.content.Context
import com.chorokbul.data.repository.InsertRawDataRepository
import com.chorokbul.datastore.AppPrefsDataSource
import com.dao.RawDataDao
import com.utils.GetRawDataHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class InsertRawDataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: RawDataDao,
    private val appPrefsDataSource: AppPrefsDataSource
) : InsertRawDataRepository {
    override suspend fun insertSoundSignalRawData() {
        val soundSignalRawData = GetRawDataHelper.getSoundSignalList(context)
        dao.insertSoundSignalData(soundSignalRawData)
        appPrefsDataSource.updateSoundSignalDownloadedState(complete = true)
    }
}
