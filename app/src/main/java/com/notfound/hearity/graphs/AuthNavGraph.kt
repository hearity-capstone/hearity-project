package com.notfound.hearity.graphs

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.notfound.hearity.ui.screens.authentication.LoginScreen
import com.notfound.hearity.ui.screens.authentication.SignUpScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController) {
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ) {
        composable(
            route = AuthScreen.Login.route
        ) {
            LoginScreen(
                onLoginClick = {
                    navController.navigate(Graph.MAIN) {
                        // clear backstack
                        popUpTo(Graph.AUTHENTICATION) {
                            inclusive = true
                        }
                    }
                },
                onSignUpClick = { navController.navigate(AuthScreen.SignUp.route) }
            )
        }
        composable(
            route = AuthScreen.SignUp.route
        ) {
            SignUpScreen(
                onClick = { navController.navigate(AuthScreen.Login.route) }
            )
        }
    }
}


sealed class AuthScreen(val route: String) {
    data object Login : AuthScreen(route = "LOGIN")
    data object SignUp : AuthScreen(route = "SIGN_UP")
}