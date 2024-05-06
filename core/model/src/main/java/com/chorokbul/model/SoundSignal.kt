package com.chorokbul.model

import ted.gun0912.clustering.clustering.TedClusterItem
import ted.gun0912.clustering.geometry.TedLatLng

data class SoundSignal(
    val id: Int,
    val isAcousticSound: Boolean,
    val lat: Double,
    val lng: Double,
): TedClusterItem {
    override fun getTedLatLng(): TedLatLng {
        return TedLatLng(
            latitude = lat,
            longitude = lng,
        )
    }
}
