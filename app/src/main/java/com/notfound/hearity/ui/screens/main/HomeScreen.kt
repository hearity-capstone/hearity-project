package com.notfound.hearity.ui.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.notfound.hearity.graphs.navigateToProfileGraph
import com.notfound.hearity.graphs.navigateToTestHistoryScreen
import com.notfound.hearity.ui.common.SectionTitle
import com.notfound.hearity.ui.screens.main.home.AudiometryGraph
import com.notfound.hearity.ui.screens.main.home.TestHistory
import com.notfound.hearity.ui.screens.main.home.TreatmentPlanSection
import com.notfound.hearity.ui.theme.IconSizeLarge
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.SpacingItem
import com.notfound.hearity.ui.theme.SpacingSection
import com.notfound.hearity.ui.theme.SpacingSectionLarge
import com.notfound.hearity.ui.theme.SpacingSmall

@Composable
fun HomeScreen(
    rootNavController: NavHostController,
) {
    Scaffold(
        contentWindowInsets = WindowInsets(0.dp),
        topBar = { TopBar(onProfileClick = { rootNavController.navigateToProfileGraph() }) }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .padding(
                    top = it.calculateTopPadding(),
                    end = PaddingMedium,
                    start = PaddingMedium
                ),
        ) {
            Spacer(Modifier.height(SpacingItem))
            AudiometryGraph()
            Spacer(Modifier.height(SpacingSection))
            TreatmentPlanSection()
            Spacer(Modifier.height(SpacingSectionLarge))
            SectionTitle(
                title = "Test History",
                icon = Icons.Filled.History,
                actionTitle = "See all",
                action = { rootNavController.navigateToTestHistoryScreen() })
            Spacer(Modifier.height(SpacingSection))
            TestHistory(rootNavController = rootNavController)
            Spacer(Modifier.height(SpacingItem))
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun TopBar(onProfileClick: () -> Unit = {}) {
    TopAppBar(
        title = {
            Column {
                Row {
                    Text(
                        "Hello,",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal)
                            .copy(color = MaterialTheme.colorScheme.onSurface),
                    )
                    Spacer(Modifier.width(SpacingSmall))
                    Text(
                        "User",
                        style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
                            .copy(color = MaterialTheme.colorScheme.onSurface),
                    )
                }
                Text(
                    "Have a nice day!",
                    style = MaterialTheme.typography.labelSmall.copy(
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    ),
                )
            }
        },
        actions = {
            IconButton(onClick = { onProfileClick() }) {
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.size(IconSizeLarge)
                )
            }
        }
    )
}