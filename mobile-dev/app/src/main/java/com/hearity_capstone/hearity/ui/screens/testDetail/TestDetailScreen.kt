package com.hearity_capstone.hearity.ui.screens.testDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
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
import com.hearity_capstone.hearity.ui.theme.SpacingItem

@Composable
fun TestDetailScreen(navController: NavHostController, id: Int? = null) {
    Scaffold(
        topBar = { AppTopBar(navController, "Test Details") }
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
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.clickable { },
                    text = "Test Details",
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(SpacingItem))
                Text(
                    modifier = Modifier.clickable { },
                    text = "id: " + id.toString(),
                    fontWeight = FontWeight.Bold
                )
            }

        }
    }
}