package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.graphs.navigateToTestDetailScreen
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import java.time.LocalDate


@Composable
fun TestHistory(
    modifier: Modifier = Modifier,
    rootNavController: NavHostController
) {
    val testResults = listOf(
        TestResult(
            1,
            LocalDate.now(),
            "dr. Ratna Kusuma, Sp.THT-KL",
            "Your hearing is normal at low to medium frequencies. However, there is a mild hearing loss detected at high frequencies in your right ear. This might affect your ability to hear high-pitched sounds clearly. It is recommended to consult with an audiologist for further evaluation and guidance.",
            46,
            30
        ),
        TestResult(
            2,
            LocalDate.now().minusDays(5), "dr. Ratna Kusuma, Sp.THT-KL",
            "Your test results indicate a mild hearing loss at high frequencies, particularly in your right ear. This could impact your ability to hear certain consonants and high-pitched sounds. We recommend scheduling a follow-up appointment with an audiologist to discuss your results in detail and explore potential solutions.",
            63,
            32
        ),
        TestResult(
            3,
            LocalDate.now().minusDays(10), "dr. Natalia Wijaya, Sp.THT-KL",
            "Your hearing test reveals a mild hearing loss at high frequencies in your right ear. This could make it challenging to hear certain sounds clearly in noisy environments. We recommend consulting with an audiologist to discuss your results and determine the best course of action. They can provide personalized recommendations and guidance based on your individual needs.",
            42,
            91
        )
    )

    Column(
        modifier = modifier
            .fillMaxWidth()
    ) {

        testResults.forEach { result ->
            TestResultCard(
                result,
                onSeeDetailsClick = { rootNavController.navigateToTestDetailScreen(result.id) })
            Spacer(modifier = Modifier.height(SpacingItem))
        }
    }
}
