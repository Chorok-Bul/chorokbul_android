package com.chorokbul.map.ui

import android.speech.tts.TextToSpeech
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chorokbul.resource.R
import java.util.Locale

@Composable
fun TTSBox(
    modifier: Modifier = Modifier,
    step: Int,
    isTTS: Boolean,
) {
    val context = LocalContext.current
    var textToSpeech: TextToSpeech? by remember { mutableStateOf(null) }
    val text by remember { mutableStateOf("앞으로 ${step}걸음만 더 걸으면 횡단보도가 나옵니다.") }

    // TextToSpeech 초기화
    LaunchedEffect(Unit) {
        textToSpeech = TextToSpeech(context) { status ->
            if (status == TextToSpeech.SUCCESS) {
                textToSpeech?.setLanguage(Locale.KOREAN)
            }
        }
    }

    LaunchedEffect(step) {
        if (!isTTS) return@LaunchedEffect
        val tts = textToSpeech ?: return@LaunchedEffect
        tts.apply {
            setPitch(1f) // 음성 톤 높이 지정
            setSpeechRate(4f) // 음성 속도 -> 시각 장애인 들은 듣기 속도가 비장애인 보다 빠르게 듣기 때문에 속도를 빠르게 해야함. TODO : 속도 빠르기 기준은 정해야 함
            speak(text, TextToSpeech.QUEUE_FLUSH, null, null)
        }
    }

    Text(
        text = text,
        textAlign = TextAlign.Center,
        modifier = modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                width = 1.dp,
                color = colorResource(id = R.color.light_gray),
                shape = RoundedCornerShape(8.dp)
            )
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 28.dp),
    )
}