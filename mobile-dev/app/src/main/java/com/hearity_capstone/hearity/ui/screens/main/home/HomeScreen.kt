package com.hearity_capstone.hearity.ui.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.graphs.navigateToProfileGraph
import com.hearity_capstone.hearity.graphs.navigateToTestHistoryScreen
import com.hearity_capstone.hearity.ui.common.SectionTitle
import com.hearity_capstone.hearity.ui.screens.main.home.components.TestHistory
import com.hearity_capstone.hearity.ui.screens.main.home.components.TreatmentPlanSection
import com.hearity_capstone.hearity.ui.screens.main.home.components.audiometryCard.AudiometryCard
import com.hearity_capstone.hearity.ui.theme.IconSizeLarge
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.ui.theme.SpacingSectionLarge
import com.hearity_capstone.hearity.ui.theme.SpacingSmall

@Composable
fun HomeScreen(
    rootNavController: NavHostController,
) {
    Column(
        Modifier.padding(horizontal = PaddingMedium)
    ) {
        TopBar(onProfileClick = { rootNavController.navigateToProfileGraph() })
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            Spacer(Modifier.height(SpacingItem))
            AudiometryCard()
            Spacer(Modifier.height(SpacingSection))
            TreatmentPlanSection()
            Spacer(Modifier.height(SpacingSectionLarge))
            SectionTitle(
                title = "Test History",
                icon = Icons.Filled.History,
                actionTitle = "See All",
                action = { rootNavController.navigateToTestHistoryScreen() })
            Spacer(Modifier.height(SpacingSection))
            TestHistory(rootNavController = rootNavController)
            Spacer(Modifier.height(SpacingItem))
        }
    }
}

@Preview
@Composable
fun TopBar(onProfileClick: () -> Unit = {}) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = PaddingSmall),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Row {
                Text(
                    "Hello,",
                    style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Normal)
                        .copy(color = MaterialTheme.colorScheme.onSurface),
                )
                Spacer(Modifier.width(SpacingSmall))
                Text(
                    "John",
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
        IconButton(onClick = { onProfileClick() }) {
            Icon(
                painter = painterResource(R.drawable.avatar_dylan),
                contentDescription = "Profile Avatar",
                tint = Color.Unspecified,
                modifier = Modifier
                    .size(IconSizeLarge)
                    .clip(CircleShape)
                    .background(color = MaterialTheme.colorScheme.inverseSurface)
                    .padding(1.dp)
            )
        }
    }
}