package com.notfound.hearity.graphs

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notfound.hearity.ui.animation.scaleFadeEnterTransition
import com.notfound.hearity.ui.animation.scaleFadeExitTransition
import com.notfound.hearity.ui.animation.slideFromRightEnterTransition
import com.notfound.hearity.ui.animation.slideToRightExitTransition
import com.notfound.hearity.ui.screens.authentication.LoginScreen
import com.notfound.hearity.ui.screens.authentication.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(
            enterTransition = { scaleFadeEnterTransition() },
            exitTransition = { scaleFadeExitTransition() },
            route = AuthScreen.Login.route
        ) {
            LoginScreen(navController)
        }
        composable(
            enterTransition = { slideFromRightEnterTransition() },
            exitTransition = { slideToRightExitTransition() },
            route = AuthScreen.SignUp.route
        ) {
            SignUpScreen(navController)
        }
    }
}

sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
}

fun NavController.navigateToLogin() {
    this.navigate(AuthScreen.Login.route)
}

fun NavController.navigateToSignUp() {
    this.navigate(AuthScreen.SignUp.route)
}
