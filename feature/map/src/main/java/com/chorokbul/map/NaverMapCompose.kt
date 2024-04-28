package com.chorokbul.map

import android.view.Gravity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import com.chorokbul.resource.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationOverlay
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import java.util.Locale

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMapScreen() {
    val cameraPositionState = rememberCameraPositionState()
    var currentPosition by remember {
        mutableStateOf(cameraPositionState.position.target)
    }

    val mapProperties by remember {
        mutableStateOf(
            MapProperties(
                maxZoom = 17.0,
                minZoom = 12.0,
                symbolScale = 2.0f, // 지도의 글자 크기 scale 조절 -> 기본 1.0f
                locationTrackingMode = LocationTrackingMode.Follow,
            )
        ) // 기준을 아직 정하지 않음.
    }
    val mapUiSettings by remember {
        mutableStateOf(
            MapUiSettings(
                isZoomControlEnabled = false,
                isLogoClickEnabled = false,
            )
        )
    }

    NaverMap(
        modifier = Modifier
            .fillMaxSize(),
        locationSource = rememberFusedLocationSource(),
        cameraPositionState = cameraPositionState,
        properties = mapProperties,
        uiSettings = mapUiSettings,
        onLocationChange = { location ->
            currentPosition = LatLng(location.latitude, location.longitude)
        },
        locale = Locale.KOREA,
    ) {
        Marker(
            state = MarkerState(position = LatLng(37.532600, 127.024612)),
            icon = OverlayImage.fromResource(R.drawable.traffic_light),
        )

        LocationOverlay(
            position = currentPosition,
            icon = OverlayImage.fromResource(R.drawable.my_location)
        )
    }
}