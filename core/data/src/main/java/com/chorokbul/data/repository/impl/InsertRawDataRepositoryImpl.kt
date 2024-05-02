package com.chorokbul.data.repository.impl

import android.content.Context
import com.chorokbul.data.repository.InsertRawDataRepository
import com.chorokbul.database.dao.RawDataDao
import com.chorokbul.database.utils.GetRawDataHelper
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class InsertRawDataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: RawDataDao
) : InsertRawDataRepository {
    override suspend fun insertSoundSignalRawData() {
        val soundSignalRawData = GetRawDataHelper.getSoundSignalList(context)
        dao.insertSoundSignalData(soundSignalRawData)
    }
}
