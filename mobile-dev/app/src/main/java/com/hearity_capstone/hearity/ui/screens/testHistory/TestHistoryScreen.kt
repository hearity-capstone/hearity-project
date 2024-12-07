package com.hearity_capstone.hearity.ui.screens.testHistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.data.dummy.testResultDummyData
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.common.SectionTitle
import com.hearity_capstone.hearity.ui.common.testResultCard.TestResultCard
import com.hearity_capstone.hearity.ui.screens.testHistory.components.AppCalendarView
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingSection

@Composable
fun TestHistoryScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AppTopBar(navController, title = "Test History") }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = PaddingMedium)
        ) {
            AppCalendarView()
            Spacer(Modifier.height(SpacingSection))
            SectionTitle(title = "Test Result", icon = Icons.Default.History)
            Spacer(Modifier.height(SpacingSection))
            TestResultCard(testResultDummyData[0], onSeeDetailsClick = {})
        }
    }
}
