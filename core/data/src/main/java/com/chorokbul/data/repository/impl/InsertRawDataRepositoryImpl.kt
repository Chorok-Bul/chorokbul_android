package com.chorokbul.data.repository.impl

import android.content.Context
import com.chorokbul.data.repository.InsertRawDataRepository
import com.dao.RawDataDao
import com.utils.DataUtils
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

internal class InsertRawDataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dao: RawDataDao
) : InsertRawDataRepository {
    override suspend fun insertSoundSignalRawData() {
        val soundSignalRawData = DataUtils.getSoundSignalList(context)
        dao.insertSoundSignalData(soundSignalRawData)
    }
}
