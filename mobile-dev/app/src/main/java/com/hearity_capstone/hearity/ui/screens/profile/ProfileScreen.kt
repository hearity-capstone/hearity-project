package com.hearity_capstone.hearity.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.theme.AvatarSizeLarge
import com.hearity_capstone.hearity.ui.theme.IconSizeMedium
import com.hearity_capstone.hearity.ui.theme.IconSizeSmall
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.PaddingSmall
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.ui.theme.SpacingSmall
import com.hearity_capstone.hearity.viewModel.AuthViewModel


@Composable
fun ProfileScreen(navController: NavHostController, authViewModel: AuthViewModel) {
    val userState = authViewModel.loginState.collectAsState()
    var user = userState.value

    var openAvatarSelectorDialog by remember { mutableStateOf(false) }
    var openEditProfileDialog by remember { mutableStateOf(false) }
    var openSettingsDialog by remember { mutableStateOf(false) }
    var openAboutDialog by remember { mutableStateOf(false) }

    if (openAvatarSelectorDialog) {
        AvatarSelectorDialog(
            onAvatarSelected = { openAvatarSelectorDialog = !openAvatarSelectorDialog },
            onCancel = { openAvatarSelectorDialog = !openAvatarSelectorDialog }
        )
    }

    if (openEditProfileDialog) {
        EditProfileDialog(
            onCancel = { openEditProfileDialog = !openEditProfileDialog },
            onSave = { openEditProfileDialog = !openEditProfileDialog })
    }
    if (openSettingsDialog) {
        SettingsDialog(
            onCancel = { openSettingsDialog = !openSettingsDialog },
            onSave = { openSettingsDialog = !openSettingsDialog })
    }
    if (openAboutDialog) {
        AboutDialog(
            onCancel = { openAboutDialog = !openAboutDialog },
        )
    }

    Scaffold(
        topBar = { AppTopBar(navController, title = "Profile") }
    ) {
        Column(
            modifier = Modifier.padding(
                top = it.calculateTopPadding(),
                start = PaddingMedium,
                end = PaddingMedium
            )
        ) {
            Row(
                modifier = Modifier.padding(vertical = PaddingSmall, horizontal = PaddingMedium),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box {
                    Icon(
                        modifier = Modifier
                            .size(AvatarSizeLarge)
                            .clip(CircleShape)
                            .background(color = MaterialTheme.colorScheme.inverseSurface)
                            .padding(1.dp)
                            .clickable { openAvatarSelectorDialog = !openAvatarSelectorDialog },
                        painter = painterResource(R.drawable.avatar_dylan),
                        contentDescription = "Profile Avatar",
                        tint = Color.Unspecified
                    )
                    Icon(
                        imageVector = Icons.Outlined.CameraAlt,
                        contentDescription = "Profile Avatar",
                        tint = MaterialTheme.colorScheme.onSurface,
                        modifier = Modifier
                            .size(IconSizeSmall)
                            .align(Alignment.BottomEnd)
                            .clip(MaterialTheme.shapes.large)
                            .background(color = MaterialTheme.colorScheme.secondaryContainer)
                            .padding(2.dp)
                    )
                }
                Spacer(Modifier.width(SpacingSection))
                Column {
                    Text(
                        user?.firstName + " " + user?.lastName,
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(SpacingSmall))
                    Text(user?.email.toString(), style = MaterialTheme.typography.bodySmall)
                }
                Spacer(Modifier.weight(1f))
                IconButton(
                    onClick = { openEditProfileDialog = !openEditProfileDialog },
                ) {
                    Image(
                        painter = painterResource(R.drawable.ic_edit_outline),
                        contentDescription = "stethoscope icon",
                        modifier = Modifier.size(IconSizeMedium),
                        colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
                        alignment = Alignment.Center
                    )
                }
            }
            Spacer(Modifier.height(SpacingSection))
            HorizontalDivider(
                thickness = 1.dp,
                color = MaterialTheme.colorScheme.outlineVariant
            )
            Spacer(Modifier.height(SpacingSection))
            ProfileMenuItem(
                icon = R.drawable.ic_settings_outline,
                text = "Settings",
                onClick = { openSettingsDialog = !openSettingsDialog })
            ProfileMenuItem(icon = R.drawable.ic_info_outline, text = "About", onClick = {
                openAboutDialog = !openAboutDialog
            })
        }
    }
}

@Composable
fun ProfileMenuItem(
    icon: Int,
    text: String,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable { onClick() }
            .padding(vertical = PaddingSmall)
            .height(36.dp)
            .padding(horizontal = SpacingSection)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Image(
            painter = painterResource(icon),
            contentDescription = null,
            colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSurface),
            modifier = Modifier.size(IconSizeMedium)
        )
        Spacer(Modifier.width(SpacingSection))
        Text(text, style = MaterialTheme.typography.bodyMedium)
    }
}
