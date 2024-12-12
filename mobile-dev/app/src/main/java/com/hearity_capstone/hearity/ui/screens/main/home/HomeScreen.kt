package com.hearity_capstone.hearity.ui.screens.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
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
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.History
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.graphs.navigateToAddTestResultScreen
import com.hearity_capstone.hearity.graphs.navigateToProfileGraph
import com.hearity_capstone.hearity.graphs.navigateToTestHistoryScreen
import com.hearity_capstone.hearity.ui.common.LoadingDialog
import com.hearity_capstone.hearity.ui.common.SectionTitle
import com.hearity_capstone.hearity.ui.screens.main.home.components.AudiometryCard
import com.hearity_capstone.hearity.ui.screens.main.home.components.TestHistory
import com.hearity_capstone.hearity.ui.screens.main.home.components.TreatmentPlanSection
import com.hearity_capstone.hearity.ui.theme.IconSizeLarge
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.ui.theme.SpacingSectionLarge
import com.hearity_capstone.hearity.ui.theme.SpacingSmall
import com.hearity_capstone.hearity.viewModel.AuthViewModel
import com.hearity_capstone.hearity.viewModel.TestResultViewModel

@Composable
fun HomeScreen(
    rootNavController: NavHostController,
    authViewModel: AuthViewModel,
    testResultViewModel: TestResultViewModel
) {


    val isTestResultLoading by testResultViewModel.isLoading.collectAsState()

    val userState = authViewModel.loginState.collectAsState()
    var user = userState.value

    if (isTestResultLoading) {
        LoadingDialog("Loading Test Results..")
    }

    Box(Modifier.fillMaxSize()) {
        Column(
            Modifier
                .padding(horizontal = PaddingMedium)
                .fillMaxSize()
        ) {
            TopBar(
                onProfileClick = { rootNavController.navigateToProfileGraph() },
                userFirstName = user?.firstName ?: ""
            )
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
        FAB(
            Modifier
                .align(Alignment.BottomEnd)
                .padding(PaddingMedium),
            onClick = { rootNavController.navigateToAddTestResultScreen() })
    }
}

@Composable
fun TopBar(onProfileClick: () -> Unit = {}, userFirstName: String) {

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
                    userFirstName,
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

@Composable
private fun FAB(modifier: Modifier = Modifier, onClick: () -> Unit) {
    FloatingActionButton(
        modifier = modifier,
        onClick = { onClick() },
        elevation = FloatingActionButtonDefaults.elevation(
            defaultElevation = 0.dp,
            pressedElevation = 0.dp
        ),
        containerColor = MaterialTheme.colorScheme.primary.copy(alpha = 0.8f)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            tint = MaterialTheme.colorScheme.onPrimary,
            contentDescription = "Add Audiometry Test Result",
        )
    }
}