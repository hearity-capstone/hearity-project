package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.wear.compose.material.Icon
import com.hearity_capstone.hearity.ui.common.AppCard
import com.hearity_capstone.hearity.ui.common.AppCardSize
import com.hearity_capstone.hearity.ui.theme.IconContainerSizeMedium
import com.hearity_capstone.hearity.ui.theme.SpacingMedium

@Composable
fun FindNearbyHospitalCard(modifier: Modifier = Modifier) {
    AppCard(
        modifier = modifier,
        size = AppCardSize.LARGE,
        onClick = { }
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(IconContainerSizeMedium)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.primary.copy(alpha = 0.75f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = Icons.Rounded.Search, contentDescription = null)
            }
            Spacer(Modifier.width(SpacingMedium))
            Text(
                "Find Nearby\nHospital", style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.Bold, color = MaterialTheme.colorScheme.primary,
                ), maxLines = 2, overflow = TextOverflow.Ellipsis
            )
        }
    }
}