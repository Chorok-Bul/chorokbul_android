package com.chorokbul.database.utils

import android.content.Context
import com.chorokbul.database.entity.SoundSignalEntity
import com.chorokbul.database.common.Constants.RAW_DATA_SOUND_SIGNAL

/**
 * GetRawDataHelper는 raw Data 파일을 open해서 raw data를 list<Entity>로 변환 해주는 유틸
 *
 */
object GetRawDataHelper {

    fun getSoundSignalList(context: Context): List<SoundSignalEntity> {
        val soundSignalList = openRawData(context, RAW_DATA_SOUND_SIGNAL)
            .map {
                val token = it.split("\t")
                val id = token[1].trim().toInt()
                val lat = token[2].trim().toDouble()
                val lng = token[3].trim().toDouble()
                SoundSignalEntity(
                    id = id,
                    lat = lat,
                    lng = lng
                )
            }
        return soundSignalList
    }

    private fun openRawData(context: Context, rawDataName: String): List<String> {
        return context.assets.open(rawDataName)
            .bufferedReader()
            .readLines()
    }
}
