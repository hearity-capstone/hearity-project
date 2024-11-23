package com.notfound.hearity.graphs

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notfound.hearity.ui.screens.ScreenContent
import com.notfound.hearity.ui.screens.profile.ProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreen.Profile.route
    ) {
        composable(
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = {it},
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = {it},
                    animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
                )
            },
            route = ProfileScreen.Profile.route) {
            ProfileScreen()
        }
        composable(route = ProfileScreen.Details.route) {
            ScreenContent(name = ProfileScreen.Details.title) {
                navController.popBackStack(
                    route = ProfileScreen.Details.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class ProfileScreen(
    val route: String,
    val title: String
) {
    data object Profile : ProfileScreen(route = "PROFILE", title = "Profile")
    data object Details : ProfileScreen(route = "DETAILS", title = "Details")
}