package com.chorokbul.android.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.chorokbul.map.NaverMapScreen
import com.chorokbul.resource.R
import com.chorokbul.splash.SplashScreen

@Composable
fun MainNavHost(
    modifier: Modifier,
    navController: NavHostController,
    paddingValues: PaddingValues,
) {
    NavHost(
        navController = navController,
        startDestination = RouteScreen.Splash.screenRoute,
        modifier = modifier
            .padding(paddingValues),
    ) {

        // SplashScreen
        val splashRoute = RouteScreen.Splash.screenRoute
        composable(
            route = splashRoute
        ) {
            SplashScreen(
                onComplete = { complete ->
                    if (complete) {
                        navController.navigate(
                            route = RouteScreen.Map.screenRoute,
                            builder = {
                                popUpTo(splashRoute) { inclusive = true }
                                launchSingleTop = true
                            }
                        )
                    }
                }
            )
        }

        // MapScreen
        composable(
            route = RouteScreen.Map.screenRoute
        ) {
            NaverMapScreen()
        }

        composable(
            route = RouteScreen.Setting.screenRoute,
        ) {
            // TODO: Setting Screen
            Image(
                // sample
                painter = painterResource(id = R.drawable.ico_back),
                contentDescription = "setting back icon",
                modifier = Modifier
                    .clickable { navController.popBackStack() }
                    .padding(start = 15.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)
                    .size(24.dp),
            )
        }
    }
}
