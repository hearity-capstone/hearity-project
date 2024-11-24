package com.notfound.hearity.ui.screens.main


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.material.Text
import com.notfound.hearity.R
import com.notfound.hearity.graphs.MainNavGraph
import com.notfound.hearity.ui.theme.IconSizeMedium

@Composable
fun MainScreen(
    rootNavController: NavHostController,
    navController: NavHostController = rememberNavController()
) {
    Scaffold(
//        contentWindowInsets = WindowInsets(0.dp),
        bottomBar = { BottomBar(navController = navController) }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            MainNavGraph(rootNavController = rootNavController, navController = navController)
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
    val screens: List<BottomBarScreen> = listOf(
        BottomBarScreen.Home,
        BottomBarScreen.Profile,
        BottomBarScreen.Settings,
    )

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    val bottomBarDestination = screens.any { it.route == currentDestination?.route }
    if (bottomBarDestination) {
        NavigationBar(
        ) {
            screens.forEach { screen ->
                AddItems(
                    screen = screen,
                    currentDestination = currentDestination,
                    navController = navController
                )
            }
        }
    }
}


@Composable
fun RowScope.AddItems(
    screen: BottomBarScreen,
    currentDestination: NavDestination?,
    navController: NavHostController,
) {
    val isSelected = currentDestination?.hierarchy?.any { it.route == screen.route } == true

    NavigationBarItem(
        alwaysShowLabel = false,
        label = {
            Text(
                text = screen.title,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.labelSmall
            )
        },
        icon = {
            Image(
                painter = painterResource(id = if (isSelected) screen.iconSelected else screen.icon),
                screen.title,
                modifier = Modifier.size(IconSizeMedium),
                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
            )
        },
        selected = isSelected,
        onClick = {
            navController.navigate(screen.route) {
                popUpTo(navController.graph.findStartDestination().id)
                launchSingleTop = true
            }
        })
}

sealed class BottomBarScreen(
    val route: String,
    val title: String,
    val icon: Int,
    val iconSelected: Int,
) {
    data object Home : BottomBarScreen(
        route = "HOME",
        title = "Home",
        icon = R.drawable.ic_home_outline,
        iconSelected = R.drawable.ic_home_filled

    )

    data object Profile : BottomBarScreen(
        route = "CHATBOT",
        title = "Chatbot",
        icon = R.drawable.ic_chat_bubble_outline,
        iconSelected = R.drawable.ic_chat_bubble_filled
    )

    data object Settings : BottomBarScreen(
        route = "FILES",
        title = "Files",
        icon = R.drawable.ic_folder_outline,
        iconSelected = R.drawable.ic_folder_filled
    )
}