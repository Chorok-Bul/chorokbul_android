package com.chorokbul.android.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
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
    isTTS: Boolean,
) {
    NavHost(
        navController = navController,
        startDestination = RouteScreen.Splash.screenRoute,
        modifier = modifier
            .padding(paddingValues),
        enterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        exitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Left,
                animationSpec = tween(700)
            )
        },
        popEnterTransition = {
            slideIntoContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
        popExitTransition = {
            slideOutOfContainer(
                AnimatedContentTransitionScope.SlideDirection.Right,
                animationSpec = tween(700)
            )
        },
    ) {

        // SplashScreen
        composable(
            route = RouteScreen.Splash.screenRoute
        ) {
            SplashScreen(
                onComplete = { complete ->
                    if (complete) {
                        val route = RouteScreen.Map.screenRoute
                        navController.navigate(
                            route = route,
                            builder = {
                                popUpTo(route) { saveState = false }
                                launchSingleTop = true
                                restoreState = false
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
            NaverMapScreen(isTTS = isTTS)
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
                    .fillMaxSize()
                    .clickable { navController.popBackStack() }
                    .padding(start = 15.dp, top = 16.dp, bottom = 16.dp, end = 16.dp)
                    .size(24.dp),
            )
        }
    }
}
