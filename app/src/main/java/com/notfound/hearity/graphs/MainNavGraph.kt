package com.notfound.hearity.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notfound.hearity.ui.screens.ScreenContent
import com.notfound.hearity.ui.screens.main.BottomBarScreen
import com.notfound.hearity.ui.screens.main.HomeScreen
import com.notfound.hearity.ui.screens.main.ChatbotScreen
import com.notfound.hearity.ui.screens.main.FilesScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.MAIN,
        startDestination = BottomBarScreen.Home.route,
    ) {
        composable(route = BottomBarScreen.Home.route) {
            HomeScreen(onClick = {
                navController.navigate(Graph.DETAILS)
            })
        }
        composable(route = BottomBarScreen.Profile.route) {
            ChatbotScreen()
        }
        composable(route = BottomBarScreen.Settings.route) {
            FilesScreen()
        }
        detailsNavGraph(navController = navController)
    }
}


fun NavGraphBuilder.detailsNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.DETAILS,
        startDestination = DetailsScreen.Information.route
    ) {
        composable(route = DetailsScreen.Information.route) {
            ScreenContent(name = DetailsScreen.Information.title) {
                navController.navigate(DetailsScreen.Overview.route)
            }
        }
        composable(route = DetailsScreen.Overview.route) {
            ScreenContent(name = DetailsScreen.Overview.title) {
                navController.popBackStack(
                    route = DetailsScreen.Information.route,
                    inclusive = false
                )
            }
        }
    }
}

sealed class DetailsScreen(
    val route: String,
    val title: String
) {
    data object Information : DetailsScreen(route = "INFORMATION", title = "Information")
    data object Overview : DetailsScreen(route = "OVERVIEW", title = "Overview")
}

