package com.notfound.hearity.ui.screens.profile

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.notfound.hearity.ui.common.AppTopBar
import com.notfound.hearity.ui.theme.PaddingMedium

@Composable
fun ProfileScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AppTopBar(navController, "Profile") }
    ) {
        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                start = PaddingMedium,
                end = PaddingMedium
            )
        ) {
            Text("Profile Screen")
        }
    }
}