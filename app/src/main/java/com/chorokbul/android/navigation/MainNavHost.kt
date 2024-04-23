package com.chorokbul.android.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chorokbul.map.NaverMapScreen

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = RouteScreen.MAP.name,
        modifier = modifier
            .padding(paddingValues),
    ) {
        composable(RouteScreen.MAP.name) {
            NaverMapScreen()
        }

        composable(
            route = RouteScreen.SETTING.name,
        ) {
            // TODO: Setting Screen
        }
    }
}