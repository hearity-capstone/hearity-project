package com.hearity_capstone.hearity.graphs

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.hearity_capstone.hearity.ui.animation.defaultFadeEnterTransition
import com.hearity_capstone.hearity.ui.animation.defaultFadeExitTransition
import com.hearity_capstone.hearity.ui.screens.main.BottomBarScreen
import com.hearity_capstone.hearity.ui.screens.main.chatbot.ChatbotScreen
import com.hearity_capstone.hearity.ui.screens.main.home.HomeScreen
import com.hearity_capstone.hearity.viewmodel.ChatbotViewModel
import com.hearity_capstone.hearity.viewModel.AuthViewModel
import com.hearity_capstone.hearity.viewModel.TestResultViewModel


@OptIn(ExperimentalPermissionsApi::class)
@Composable
fun MainNavGraph(
    rootNavController: NavHostController,
    navController: NavHostController,
    authViewModel: AuthViewModel,
    testResultViewModel: TestResultViewModel
) {
    val chatbotViewModel: ChatbotViewModel = viewModel()

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
            HomeScreen(
                rootNavController = rootNavController,
                authViewModel = authViewModel,
                testResultViewModel = testResultViewModel
            )
        }
        composable(
            enterTransition = { defaultFadeEnterTransition() },
            exitTransition = { defaultFadeExitTransition() },
            route = BottomBarScreen.Profile.route
        ) {
            ChatbotScreen(viewModel = chatbotViewModel)
        }
//        composable(
//            enterTransition = { defaultFadeEnterTransition() },
//            exitTransition = { defaultFadeExitTransition() },
//            route = BottomBarScreen.Settings.route
//        ) {
//            FilesScreen()
//        }
    }
}



