package com.notfound.hearity.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notfound.hearity.ui.animation.slideFromRightEnterTransition
import com.notfound.hearity.ui.animation.slideToRightExitTransition
import com.notfound.hearity.ui.screens.ScreenContent
import com.notfound.hearity.ui.screens.profile.ProfileScreen

fun NavGraphBuilder.profileNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.PROFILE,
        startDestination = ProfileScreen.Profile.route
    ) {
        composable(
            enterTransition = { slideFromRightEnterTransition() },
            exitTransition = { slideToRightExitTransition() },
            route = ProfileScreen.Profile.route) {
            ProfileScreen(navController = navController)
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