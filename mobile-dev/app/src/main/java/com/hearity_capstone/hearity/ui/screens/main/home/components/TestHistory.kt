package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.ui.common.testResultCard.TestResultCard
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.viewModel.TestResultViewModel


@Composable
fun TestHistory(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController,
    testResultViewModel: TestResultViewModel
) {
    val allTestResult = testResultViewModel.allTestResult.collectAsState()
    val testResult = allTestResult.value


    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        testResult?.forEach { result ->
            TestResultCard(
                result,
                onSeeDetailsClick = { }
//                onSeeDetailsClick = { rootNavController.navigateToTestDetailScreen(result.id) }
            )
            Spacer(modifier = Modifier.height(SpacingItem))
        }
    }
}
