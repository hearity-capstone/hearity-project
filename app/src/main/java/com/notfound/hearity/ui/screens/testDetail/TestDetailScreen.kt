package com.notfound.hearity.ui.screens.testDetail

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.notfound.hearity.ui.theme.SpacingItem

@Composable
fun TestDetailScreen(id: Int? = null) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.clickable { },
                text = "Test Detail",
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