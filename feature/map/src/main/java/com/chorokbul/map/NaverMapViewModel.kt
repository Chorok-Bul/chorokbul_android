package com.chorokbul.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chorokbul.domain.FindLocationWithInBoundsUseCase
import com.chorokbul.model.SoundSignal
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject
import kotlin.math.cos

@HiltViewModel
class NaverMapViewModel @Inject constructor(
    private val findLocationWithInBoundsUseCase: FindLocationWithInBoundsUseCase,
) : ViewModel() {
    private var isMarkerVisible by mutableStateOf(true)
    private val totalSoundSignal = mutableStateListOf<SoundSignal>()

    private var _drawSoundSignal = mutableStateListOf<SoundSignal>()
    val drawSoundSignal: List<SoundSignal> get() = _drawSoundSignal

    fun getIsMarkerVisible() = isMarkerVisible
    fun toggleTrafficLights() {
        isMarkerVisible = !isMarkerVisible
    }

    fun findLocationInBounds(latLng: LatLng) {
        val distance = 2.0 // km 단위
        val approximate = 111.32

        val deltaLat = distance / approximate // 위도 변화량
        val deltaLng = distance / (approximate * cos(Math.toRadians(latLng.latitude))) // 경도 변화량

        val leftLat = latLng.latitude - deltaLat
        val rightLat = latLng.latitude + deltaLat
        val leftLng = latLng.longitude - deltaLng
        val rightLng = latLng.longitude + deltaLng

        viewModelScope.launch {
            findLocationWithInBoundsUseCase(leftLat, leftLng, rightLat, rightLng)
                .catch {
                    // TODO: error 처리
                }
                .collect {
                    Timber.d("list $it")
                    val newSoundSignal = findNonOverlappingElements(totalSoundSignal, it)
                    _drawSoundSignal.clear()
                    _drawSoundSignal.addAll(newSoundSignal)
                }
        }
    }

    private fun <T> findNonOverlappingElements(list1: List<T>, list2: List<T>): List<T> {
        // list1에서 list2에 없는 요소 추출
        val uniqueToFirst = list1.filter { it !in list2 }
        // list2에서 list1에 없는 요소 추출
        val uniqueToSecond = list2.filter { it !in list1 }

        // 두 결과를 합쳐서 반환
        return uniqueToFirst + uniqueToSecond
    }
}