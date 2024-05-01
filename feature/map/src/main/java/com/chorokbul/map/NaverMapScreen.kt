package com.chorokbul.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.chorokbul.map.ui.CustomFabButton
import com.chorokbul.map.ui.TTSBox
import com.chorokbul.map.ui.TrafficMarker
import com.chorokbul.resource.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationOverlay
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import timber.log.Timber
import kotlin.random.Random

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMapScreen(
    modifier: Modifier = Modifier,
    viewModel: NaverMapViewModel = hiltViewModel(),
    isTTS: Boolean = true,
) {
    val cameraPositionState = rememberCameraPositionState {
        this.position = CameraPosition(this.position.target, 18.0)
    }

    var currentPosition by remember {
        mutableStateOf(cameraPositionState.position.target)
    }

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(LocationTrackingMode.Follow)
    }

    val sampleStepCount = remember { mutableIntStateOf(100) }

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        NaverMap(
            modifier = Modifier
                .fillMaxSize(),
            locationSource = rememberFusedLocationSource(),
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                maxZoom = 20.0,
                minZoom = 12.0,
                locationTrackingMode = selectedOption,
            ),
            uiSettings = MapUiSettings(
                isZoomControlEnabled = false,
                isLogoClickEnabled = false,
            ),
            onLocationChange = { location ->
                onOptionSelected(LocationTrackingMode.Follow)

                currentPosition = LatLng(location.latitude, location.longitude)
            },
            onOptionChange = {
                cameraPositionState.locationTrackingMode?.let { trackingMode ->
                    onOptionSelected(trackingMode)
                }
            }
        ) {
            LocationOverlay(
                position = currentPosition,
                icon = OverlayImage.fromResource(R.drawable.my_location)
            )

            // TODO: 현위치 기준 및 지도 이동시 marker 세팅
            TrafficMarker(
                latLng = LatLng(37.4978407, 127.104124),
                isVisible = viewModel.getIsMarkerVisible()
            )
        }

        Column(
            modifier = Modifier
                .align(Alignment.TopEnd),
        ) {
            CustomFabButton(
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp),
                image = painterResource(id = R.drawable.img_walk_man),
            ) {
                viewModel.toggleTrafficLights()
            }

            CustomFabButton(
                modifier = Modifier
                    .padding(top = 16.dp, end = 16.dp),
                image = painterResource(id = R.drawable.ico_location),
            ) {
                onOptionSelected(LocationTrackingMode.Follow)
                Timber.d("my latlon : $currentPosition")
            }
        }

        TTSBox(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 14.dp)
                .padding(bottom = 55.dp),
            step = sampleStepCount.value,
            isTTS = isTTS,
        )
    }
}