package com.chorokbul.android.navigation

import androidx.annotation.DrawableRes
import com.chorokbul.resource.R

sealed class RouteScreen(
    @DrawableRes val icon: Int,
    val screenRoute: String
) {
    data object Splash : RouteScreen(R.drawable.img_chorokbul, RouteScreenType.SPLASH.name)
    data object Map : RouteScreen(R.drawable.ico_map, RouteScreenType.MAP.name)
    data object Blank : RouteScreen(R.drawable.ico_map, RouteScreenType.BLANK.name)
    data object Sound : RouteScreen(R.drawable.ico_sound, RouteScreenType.SOUND.name)
    data object Setting : RouteScreen(R.drawable.ico_more, RouteScreenType.SETTING.name)
}

enum class RouteScreenType {
    MAP, SOUND, SETTING, BLANK, SPLASH
}
