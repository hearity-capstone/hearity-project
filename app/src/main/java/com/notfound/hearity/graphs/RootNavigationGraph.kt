package com.notfound.hearity.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.notfound.hearity.ui.animation.scaleFadeEnterTransition
import com.notfound.hearity.ui.screens.main.MainScreen
import com.notfound.hearity.ui.screens.testDetail.TestDetailScreen
import com.notfound.hearity.ui.screens.testHistory.TestHistoryScreen

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController)
        profileNavGraph(navController = navController)
        composable(
            enterTransition = { scaleFadeEnterTransition() },
            route = Graph.MAIN
        ) {
            MainScreen(rootNavController = navController)
        }
        composable(
            enterTransition = { scaleFadeEnterTransition() },
            route = Graph.TEST_HISTORY
        ) {
            TestHistoryScreen(navController = navController)
        }
        composable(
            enterTransition = { scaleFadeEnterTransition() },
            route = "${Graph.TEST_DETAILS}/{id}"
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")?.toIntOrNull()
            TestDetailScreen(navController = navController, id)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
    const val PROFILE = "profile_graph"
    const val TEST_HISTORY = "test_history_screen"
    const val TEST_DETAILS = "test_details_screen"
}

fun NavController.navigateToMainGraphAndClearBackStack() {
    this.navigate(Graph.MAIN) {
        popUpTo(Graph.AUTHENTICATION) {
            inclusive = true
        }
    }
}

fun NavController.navigateToProfileGraph() {
    this.navigate(Graph.PROFILE)
}

fun NavController.navigateToTestHistoryScreen() {
    this.navigate(Graph.TEST_HISTORY)
}

fun NavController.navigateToTestDetailScreen(id: Int) {
    this.navigate(Graph.TEST_DETAILS + "/$id")
}
