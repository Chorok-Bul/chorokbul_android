package com.chorokbul.map

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class NaverMapViewModel @Inject constructor(
    // TODO: get Room DB
) : ViewModel() {
    private var isMarkerVisible by mutableStateOf(true)

    fun getIsMarkerVisible() = isMarkerVisible
    fun toggleTrafficLights() {
        isMarkerVisible = !isMarkerVisible
    }
}