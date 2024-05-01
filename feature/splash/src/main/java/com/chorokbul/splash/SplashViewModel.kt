package com.chorokbul.splash

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chorokbul.domain.InsertRawDataUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(
    private val insertRawDataUseCase: InsertRawDataUseCase
) : ViewModel() {

    private val _completed = mutableStateOf(false)
    val complete: State<Boolean> get() = _completed

    init {
        viewModelScope.launch {
            async(Dispatchers.IO) { insertRawDataUseCase() }.await()
            _completed.value = true
        }
    }
}
