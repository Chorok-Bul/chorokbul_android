package com.chorokbul.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chorokbul.domain.GetIsDataDownloadedUseCase
import com.chorokbul.domain.InsertRawDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val insertRawDataUseCase: InsertRawDataUseCase,
    private val getIsDataDownloadedUseCase: GetIsDataDownloadedUseCase
) : ViewModel() {

    private val _completed = mutableStateOf(false)
    val complete: State<Boolean> get() = _completed

    init {
        getIsDataDownloadedUseCase.getIsSoundSignalDownloaded().onEach { isDownloaded ->
            _completed.value = isDownloaded
            if (!isDownloaded) downloadSoundSignal()
        }.launchIn(viewModelScope)
    }

    private fun downloadSoundSignal() = viewModelScope.launch {
        async(Dispatchers.IO) { insertRawDataUseCase() }.await()
    }
}
