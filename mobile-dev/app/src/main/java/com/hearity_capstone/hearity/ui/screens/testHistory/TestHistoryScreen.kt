package com.hearity_capstone.hearity.ui.screens.testHistory

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.graphs.navigateToTestDetailScreen
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.common.SectionTitle
import com.hearity_capstone.hearity.ui.common.testResultCard.TestResultCard
import com.hearity_capstone.hearity.ui.screens.testHistory.components.AppCalendarView
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.viewModel.TestResultViewModel

@Composable
fun TestHistoryScreen(
    navController: NavHostController,
    testResultViewModel: TestResultViewModel
) {
    val allTestResult = testResultViewModel.allTestResult.collectAsState()

    Scaffold(
        topBar = { AppTopBar(navController, title = "Test History") }
    ) {

        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(it)
                .padding(horizontal = PaddingMedium)
        ) {
            AppCalendarView()
            Spacer(Modifier.height(SpacingSection))
            SectionTitle(title = "Test Result", icon = Icons.Default.History)
            Spacer(Modifier.height(SpacingSection))
            Column(Modifier.fillMaxSize()) {
                allTestResult.value?.forEach { result ->
                    TestResultCard(
                        result,
                        onSeeDetailsClick = { navController.navigateToTestDetailScreen(result.id) }
                    )
                    Spacer(modifier = Modifier.height(SpacingItem))
                }
            }
        }
    }
}
