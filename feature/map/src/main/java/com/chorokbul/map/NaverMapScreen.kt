package com.chorokbul.map

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.chorokbul.resource.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraPosition
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
import timber.log.Timber

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMapScreen(
    modifier: Modifier = Modifier,
) {
    val cameraPositionState = rememberCameraPositionState {
        this.position = CameraPosition(this.position.target, 18.0)
    }
    var currentPosition by remember {
        mutableStateOf(cameraPositionState.position)
    }

    val (selectedOption, onOptionSelected) = remember { mutableStateOf(LocationTrackingMode.Follow) }

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
                currentPosition =
                    CameraPosition(LatLng(location.latitude, location.longitude), 18.0)
            },
            onOptionChange = {
                cameraPositionState.locationTrackingMode?.let { trackingMode ->
                    onOptionSelected(trackingMode)
                }
            }
        ) {
            Marker(
                state = MarkerState(position = LatLng(37.532600, 127.024612)),
                icon = OverlayImage.fromResource(R.drawable.traffic_light),
            )

            LocationOverlay(
                position = currentPosition.target,
                icon = OverlayImage.fromResource(R.drawable.my_location)
            )
        }

        Image(
            painter = painterResource(id = R.drawable.img_walk_man),
            contentDescription = "traffic Icon",
            colorFilter = ColorFilter.tint(colorResource(id = R.color.green)),
            modifier = Modifier
                .padding(top = 16.dp, end = 16.dp)
                .align(Alignment.TopEnd)
                .shadow(
                    elevation = 5.dp,
                    shape = CircleShape
                )
                .background(
                    color = Color.White,
                    shape = CircleShape,
                )
                .clickable {
                    // TODO: 신호등 마커 on/off
                }
                .padding(12.dp),
        )

        Image(
            painter = painterResource(id = R.drawable.ico_location),
            contentDescription = "traffic Icon",
            colorFilter = ColorFilter.tint(colorResource(id = R.color.green)),
            modifier = Modifier
                .padding(top = 80.dp, end = 16.dp)
                .align(Alignment.TopEnd)
                .shadow(
                    elevation = 5.dp,
                    shape = CircleShape
                )
                .background(
                    color = Color.White,
                    shape = CircleShape,
                )
                .clickable {
                    onOptionSelected(LocationTrackingMode.Follow)
                }
                .padding(12.dp),
        )

        Box(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 14.dp)
                .padding(bottom = 40.dp)
        ) {
            Text(
                text = "앞으로 30걸음만 더 걸으면 횡단보도가 나옵니다.",
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .align(Alignment.Center)
                    .fillMaxWidth()
                    .padding(end = 28.dp)
                    .border(
                        width = 1.dp,
                        color = colorResource(id = R.color.light_gray),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .background(Color.White)
                    .padding(vertical = 8.dp, horizontal = 15.dp),
            )

            Image(
                painter = painterResource(id = R.drawable.img_chorokbul),
                contentDescription = "tts img chorokbul",
                modifier = Modifier
                    .size(width = 40.dp, height = 58.dp)
                    .align(Alignment.CenterEnd)
            )
        }
    }
}