package com.chorokbul.map

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chorokbul.domain.GetSoundSignalListWithInBoundsUseCase
import com.chorokbul.model.SoundSignal
import com.naver.maps.geometry.LatLng
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

@HiltViewModel
class NaverMapViewModel @Inject constructor(
    private val getSoundSignalListWithInBoundsUseCase: GetSoundSignalListWithInBoundsUseCase,
) : ViewModel() {
    private var _drawSoundSignal = mutableStateListOf<SoundSignal>()
    val drawSoundSignal: List<SoundSignal> get() = _drawSoundSignal

    fun getSoundSignalListWithInBounds(latLng: LatLng) {
        viewModelScope.launch {
            getSoundSignalListWithInBoundsUseCase(latitude = latLng.latitude, longitude = latLng.longitude)
                .catch {}
                .collect {
                    Timber.d("list : $it")
                    _drawSoundSignal.clear()
                    _drawSoundSignal.addAll(it)
                }
        }
    }
}