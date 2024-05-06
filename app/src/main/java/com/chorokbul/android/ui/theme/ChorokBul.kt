package com.chorokbul.android.ui.theme

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.BottomAppBar
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FabPosition
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.chorokbul.resource.R
import com.chorokbul.android.navigation.MainNavHost
import com.chorokbul.android.navigation.RouteScreen

@Composable
fun ChorokbulApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route
    val isTTS = remember { mutableStateOf(true) }

    Scaffold(
        modifier = modifier,
        bottomBar = {
            if (isBottomScreen(currentRoute)) {
                BottomBar(navController = navController)
            }
        },
        floatingActionButton = {
            val brush = Brush.verticalGradient(
                listOf(
                    colorResource(id = R.color.green),
                    colorResource(id = R.color.end_green)
                )
            )
            if (isBottomScreen(currentRoute)) {
                FloatingActionButton(
                    backgroundColor = Color.Transparent, // 배경색 투명으로 설정
                    shape = CircleShape,
                    elevation = FloatingActionButtonDefaults.elevation(defaultElevation = 0.dp),
                    modifier = Modifier
                        .size(75.dp)
                        .background(brush, shape = CircleShape),
                    onClick = {
                        isTTS.value = !isTTS.value
                        /* do tts */
                    }
                ) {
                    Image(
                        painter = painterResource(RouteScreen.Sound.icon),
                        contentDescription = "FAB center icon",
                        contentScale = ContentScale.FillWidth,
                        colorFilter = ColorFilter
                            .tint(color = if (isTTS.value) Color.White else Color.Black)
                    )
                }
            }
        },
        floatingActionButtonPosition = FabPosition.Center,
        isFloatingActionButtonDocked = true,
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            MainNavHost(
                modifier = modifier,
                navController = navController,
                paddingValues = it,
                isTTS = isTTS.value
            )
        }
    }
}

@Composable
fun BottomBar(
    navController: NavHostController,
    modifier: Modifier = Modifier,
) {
    val items = listOf(
        RouteScreen.Map,
        RouteScreen.Blank,
        RouteScreen.Setting,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomAppBar(
        modifier = modifier,
        backgroundColor = Color.White,
        cutoutShape = CircleShape,
    ) {
        items.forEach { screen ->
            val selected =
                currentDestination?.hierarchy?.any { it.route == screen.screenRoute } == true

            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = screen.icon),
                        contentDescription = "bottom navigation icon"
                    )
                },
                selected = selected,
                selectedContentColor = colorResource(id = R.color.green),
                unselectedContentColor = colorResource(id = R.color.gray),
                enabled = screen.screenRoute != RouteScreen.Blank.screenRoute,
                onClick = {
                    navController.navigate(screen.screenRoute) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}

private fun isBottomScreen(currentRoute: String?): Boolean {
    val screens = listOf(
        RouteScreen.Map.screenRoute,
    )

    return screens.any { it == currentRoute }
}