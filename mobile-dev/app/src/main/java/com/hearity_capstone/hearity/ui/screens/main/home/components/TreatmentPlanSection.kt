package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.ui.theme.SpacingItem

@Composable
fun TreatmentPlanSection(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(180.dp)
    ) {
        NextAudiometryTest(
            modifier = Modifier
                .fillMaxHeight()
                .weight(1.5f)
        )
        Spacer(Modifier.width(SpacingItem))
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .weight(2f)
        ) {
            HearingArticleCard(modifier = Modifier.weight(1f), navController = navController)
            Spacer(Modifier.height(SpacingItem))
            FindNearbyHospitalCard(modifier = Modifier.weight(1f))
        }
    }
}