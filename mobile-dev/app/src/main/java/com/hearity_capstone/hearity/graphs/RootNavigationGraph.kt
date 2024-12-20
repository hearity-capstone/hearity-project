package com.hearity_capstone.hearity.graphs

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.hearity_capstone.hearity.ui.animation.scaleFadeEnterTransition
import com.hearity_capstone.hearity.ui.animation.slideFromRightEnterTransition
import com.hearity_capstone.hearity.ui.animation.slideToRightExitTransition
import com.hearity_capstone.hearity.ui.screens.addTestResult.AddTestResultScreen
import com.hearity_capstone.hearity.ui.screens.article.ArticleScreen
import com.hearity_capstone.hearity.ui.screens.main.MainScreen
import com.hearity_capstone.hearity.ui.screens.profile.ProfileScreen
import com.hearity_capstone.hearity.ui.screens.testDetail.TestDetailScreen
import com.hearity_capstone.hearity.ui.screens.testHistory.TestHistoryScreen
import com.hearity_capstone.hearity.viewModel.AuthViewModel
import com.hearity_capstone.hearity.viewModel.TestResultViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun RootNavigationGraph(navController: NavHostController) {
    val authViewModel: AuthViewModel = koinViewModel()
    val testResultViewModel: TestResultViewModel = koinViewModel()

    NavHost(
        navController = navController,
        route = Graph.ROOT,
        startDestination = Graph.AUTHENTICATION
    ) {
        authNavGraph(navController = navController, authViewModel = authViewModel)
        composable(
            enterTransition = { scaleFadeEnterTransition() },
            route = Graph.MAIN
        ) {
            MainScreen(
                rootNavController = navController,
                authViewModel = authViewModel,
                testResultViewModel = testResultViewModel
            )
        }

        // Add Test Result
        composable(
            enterTransition = { scaleFadeEnterTransition() },
            route = Graph.ADD_TEST_RESULT
        ) {
            AddTestResultScreen(
                navController = navController,
                testResultViewModel = testResultViewModel
            )
        }

        // Test Result History
        composable(
            enterTransition = { scaleFadeEnterTransition() },
            route = Graph.TEST_HISTORY
        ) {
            TestHistoryScreen(
                navController = navController,
                testResultViewModel = testResultViewModel
            )
        }

        // Test Result Details
        composable(
            enterTransition = { scaleFadeEnterTransition() },
            route = "${Graph.TEST_DETAILS}/{id}"
        ) { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id")
            TestDetailScreen(
                navController = navController,
                id = id.toString(),
                testResultViewModel = testResultViewModel
            )
        }

        // Profile
        composable(
            route = Graph.PROFILE,
            enterTransition = { slideFromRightEnterTransition() },
            exitTransition = { slideToRightExitTransition() },
        ) {
            ProfileScreen(navController = navController, authViewModel = authViewModel)
        }

        // Article
        composable(
            route = Graph.ARTICLE,
            enterTransition = { scaleFadeEnterTransition() },
        ) {
            ArticleScreen(navController)
        }
    }
}

object Graph {
    const val ROOT = "root_graph"
    const val AUTHENTICATION = "auth_graph"
    const val MAIN = "main_graph"
    const val PROFILE = "profile_graph"
    const val ADD_TEST_RESULT = "add_test_result_graph"
    const val TEST_HISTORY = "test_history_screen"
    const val TEST_DETAILS = "test_details_screen"
    const val ARTICLE = "article_screen"
}

fun NavController.navigateToMainGraphAndClearBackStack() {
    this.navigate(Graph.MAIN) {
        popUpTo(Graph.AUTHENTICATION) {
            inclusive = true
        }
    }
}

fun NavController.navigateToAddTestResultScreen() {
    this.navigate(Graph.ADD_TEST_RESULT)
}

fun NavController.navigateToAuthGraphAndClearBackStack() {
    this.navigate(Graph.AUTHENTICATION) {
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

fun NavController.navigateToTestDetailScreen(id: String) {
    this.navigate(Graph.TEST_DETAILS + "/$id")
}

fun NavController.navigateToArticleScreen() {
    this.navigate(Graph.ARTICLE)
}