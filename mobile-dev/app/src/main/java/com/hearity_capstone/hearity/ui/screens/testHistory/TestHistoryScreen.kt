package com.hearity_capstone.hearity.ui.screens.testHistory

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.theme.PaddingMedium

@Composable
fun TestHistoryScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AppTopBar(navController, "Test History") }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = it.calculateTopPadding(),
                    start = PaddingMedium,
                    end = PaddingMedium
                ),
            contentAlignment = Alignment.Center
        ) {
            Text(
                modifier = Modifier.clickable { },
                text = "Test History",
                fontWeight = FontWeight.Bold
            )
        }
    }
}
