package com.notfound.hearity.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.notfound.hearity.ui.animation.defaultFadeEnterTransition
import com.notfound.hearity.ui.animation.defaultFadeExitTransition
import com.notfound.hearity.ui.screens.main.BottomBarScreen
import com.notfound.hearity.ui.screens.main.HomeScreen
import com.notfound.hearity.ui.screens.main.ChatbotScreen
import com.notfound.hearity.ui.screens.main.FilesScreen


@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    navController: NavHostController
) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(
            enterTransition = { defaultFadeEnterTransition() },
            exitTransition = { defaultFadeExitTransition() },
            route = BottomBarScreen.Home.route
        ) {
            HomeScreen(rootNavController = rootNavController)
        }
        composable(
            enterTransition = { defaultFadeEnterTransition()  },
            exitTransition = { defaultFadeExitTransition() },
            route = BottomBarScreen.Profile.route
        ) {
            ChatbotScreen()
        }
        composable(
            enterTransition = { defaultFadeEnterTransition() },
            exitTransition = { defaultFadeExitTransition() },
            route = BottomBarScreen.Settings.route
        ) {
            FilesScreen()
        }
    }
}



