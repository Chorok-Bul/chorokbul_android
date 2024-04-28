package com.utils

import SoundSignal
import android.content.Context
import com.common.Constants.RAW_DATA_SOUND_SIGNAL

/**
 * Data utils는 raw Data 파일을 open해서 raw data를 list<Entity>로 변환 해주는 유틸
 *
 */
object DataUtils {
    fun getSoundSignalList(context: Context): List<SoundSignal> {
        val soundSignalList = openRawData(context, RAW_DATA_SOUND_SIGNAL)
            .map {
                val token = it.split("\t")
                val lat = token[1].trim().toDouble()
                val lng = token[2].trim().toDouble()
                SoundSignal(
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
