package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.data.dummy.testResultDummyData
import com.hearity_capstone.hearity.graphs.navigateToTestDetailScreen
import com.hearity_capstone.hearity.ui.theme.SpacingItem


@Composable
fun TestHistory(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController
) {

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        testResultDummyData.forEach { result ->
            TestResultCard(
                result,
                onSeeDetailsClick = { rootNavController.navigateToTestDetailScreen(result.id) })
            Spacer(modifier = Modifier.height(SpacingItem))
        }
    }
}
