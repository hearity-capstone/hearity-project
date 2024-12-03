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
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.common.SectionTitle
import com.hearity_capstone.hearity.ui.screens.main.home.components.TestResult
import com.hearity_capstone.hearity.ui.screens.main.home.components.TestResultCard
import com.hearity_capstone.hearity.ui.screens.testHistory.components.AppCalendarView
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import java.time.LocalDate

@Composable
fun TestHistoryScreen(navController: NavHostController) {
    Scaffold(
        topBar = { AppTopBar(navController, "Test History") }
    ) {
        val testResult = TestResult(
            1,
            LocalDate.now(),
            "dr. Ratna Kusuma, Sp.THT-KL",
            "Your hearing is normal at low to medium frequencies. However, there is a mild hearing loss detected at high frequencies in your right ear. This might affect your ability to hear high-pitched sounds clearly. It is recommended to consult with an audiologist for further evaluation and guidance.",
            46,
            30
        )

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
            TestResultCard(testResult, onSeeDetailsClick = {})
        }
    }
}
