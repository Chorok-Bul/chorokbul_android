package com.chorokbul.android.ui.theme

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.chorokbul.android.navigation.MainNavHost

@Composable
fun Chorokbul(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
) {
    Scaffold(
        modifier = modifier,
        containerColor = MaterialTheme.colorScheme.background,
        bottomBar = {}
    ) {
        Column(
            modifier = modifier.fillMaxSize()
        ) {
            MainNavHost(modifier = modifier, navController = navController, paddingValues = it)
        }
    }
}