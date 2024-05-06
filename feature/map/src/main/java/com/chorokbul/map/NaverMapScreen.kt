package com.chorokbul.map

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.chorokbul.map.common.Constants.MAX_ZOOM
import com.chorokbul.map.common.Constants.MIN_CLUSTERING_COUNT
import com.chorokbul.map.common.Constants.MIN_ZOOM
import com.chorokbul.map.common.Constants.START_ZOOM
import com.chorokbul.map.ui.CustomFabButton
import com.chorokbul.map.ui.TTSBox
import com.chorokbul.map.ui.TrafficMarker
import com.naver.maps.map.overlay.Marker
import com.chorokbul.model.SoundSignal
import com.chorokbul.resource.R
import com.naver.maps.geometry.LatLng
import com.naver.maps.map.CameraAnimation
import com.naver.maps.map.CameraPosition
import com.naver.maps.map.CameraUpdate
import com.naver.maps.map.compose.DisposableMapEffect
import com.naver.maps.map.compose.ExperimentalNaverMapApi
import com.naver.maps.map.compose.LocationOverlay
import com.naver.maps.map.compose.LocationTrackingMode
import com.naver.maps.map.compose.MapProperties
import com.naver.maps.map.compose.MapUiSettings
import com.naver.maps.map.compose.Marker
import com.naver.maps.map.compose.NaverMap
import com.naver.maps.map.compose.rememberCameraPositionState
import com.naver.maps.map.compose.rememberFusedLocationSource
import com.naver.maps.map.overlay.OverlayImage
import ted.gun0912.clustering.MarkerManager
import ted.gun0912.clustering.naver.TedNaverClustering
import timber.log.Timber

@OptIn(ExperimentalNaverMapApi::class)
@Composable
fun NaverMapScreen(
    modifier: Modifier = Modifier,
    viewModel: NaverMapViewModel = hiltViewModel(),
    isTTS: Boolean = true,
) {
    val context = LocalContext.current

    val cameraPositionState = rememberCameraPositionState {
        this.position = CameraPosition(this.position.target, START_ZOOM)
    }

    var currentPosition by remember {
        mutableStateOf(cameraPositionState.position.target)
    }

    var currentLocation by remember {
        mutableStateOf(cameraPositionState.position.target)
    }

    val (selectedOption, onOptionSelected) = remember {
        mutableStateOf(LocationTrackingMode.Follow)
    }

    val stepCount = remember { mutableIntStateOf(100) }

    var clusterManager by remember {
        mutableStateOf<TedNaverClustering<SoundSignal>?>(null)
    }

    Box(
        modifier = modifier
            .fillMaxSize(),
    ) {
        NaverMap(
            modifier = Modifier
                .fillMaxSize(),
            locationSource = rememberFusedLocationSource().apply {
                this.activate {
                    currentLocation = LatLng(it?.latitude ?: 0.0, it?.longitude ?: 0.0)
                }
            },
            cameraPositionState = cameraPositionState,
            properties = MapProperties(
                maxZoom = MAX_ZOOM,
                minZoom = MIN_ZOOM,
                locationTrackingMode = selectedOption,
            ),
            onMapLoaded = {
                onOptionSelected(LocationTrackingMode.Follow)
            },
            uiSettings = MapUiSettings(
                isZoomControlEnabled = false,
                isLogoClickEnabled = false,
            ),
            onLocationChange = { location ->
                currentPosition = LatLng(location.latitude, location.longitude)
            },
            onOptionChange = {
                cameraPositionState.locationTrackingMode?.let { trackingMode ->
                    onOptionSelected(trackingMode)
                }
            },
        ) {
            LocationOverlay(
                position = currentLocation,
                icon = OverlayImage.fromResource(R.drawable.my_location)
            )

            DisposableMapEffect(Unit) { map ->
                if (clusterManager == null) {
                    clusterManager = TedNaverClustering.with<SoundSignal>(context, map).apply {
                        customMarker {
                            Marker(
                                LatLng(it.lat, it.lng),
                                OverlayImage.fromResource(R.drawable.traffic_light)
                            )
                        }
                        minClusterSize(MIN_CLUSTERING_COUNT)
                    }.make()
                }

                onDispose {
                    clusterManager?.clearItems()
                }
            }

            clusterManager?.addItems(viewModel.drawSoundSignal)
        }

        CustomFabButton(
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = 16.dp, end = 16.dp),
            image = painterResource(id = R.drawable.ico_location),
        ) {
            cameraPositionState.move(
                CameraUpdate
                    .toCameraPosition(CameraPosition(currentLocation, START_ZOOM))
                    .animate(CameraAnimation.Fly)
                    .finishCallback {
                        viewModel.getSoundSignalListWithInBounds(currentLocation)
                    }
            )
        }

        TTSBox(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(horizontal = 14.dp)
                .padding(bottom = 55.dp),
            step = stepCount.intValue,
            isTTS = isTTS,
        )
    }
}

fun moveToMyLocation() {

}