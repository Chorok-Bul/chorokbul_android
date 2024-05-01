package com.chorokbul.map.ui

import androidx.compose.runtime.Composable
import com.chorokbul.resource.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.MarkerState
import com.naver.maps.map.overlay.OverlayImage

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun TrafficMarker(
    latLng: LatLng,
    isVisible: Boolean = true,
) {
    Marker(
        state = MarkerState(position = LatLng(latLng.latitude, latLng.longitude)),
        icon = OverlayImage.fromResource(R.drawable.traffic_light),
        visible = isVisible
    )
}