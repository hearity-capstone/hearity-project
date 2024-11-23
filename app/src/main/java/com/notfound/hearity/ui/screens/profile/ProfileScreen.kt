package com.notfound.hearity.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.notfound.hearity.R
import com.notfound.hearity.ui.common.AppTopBar
import com.notfound.hearity.ui.theme.AvatarSizeLarge
import com.notfound.hearity.ui.theme.IconSizeMedium
import com.notfound.hearity.ui.theme.PaddingMedium
import com.notfound.hearity.ui.theme.PaddingSmall
import com.notfound.hearity.ui.theme.SpacingItem
import com.notfound.hearity.ui.theme.SpacingSection
import com.notfound.hearity.ui.theme.SpacingSmall


@Composable
fun ProfileScreen(navController: NavHostController) {
    var openEditProfileDialog by remember { mutableStateOf(false) }
    var openSettingsDialog by remember { mutableStateOf(false) }
    var openAboutDialog by remember { mutableStateOf(false) }

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
        topBar = { AppTopBar(navController, "Profile") }
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
                Icon(
                    imageVector = Icons.Filled.AccountCircle,
                    contentDescription = "Profile",
                    modifier = Modifier.size(AvatarSizeLarge)
                )
                Spacer(Modifier.width(SpacingItem))
                Column {
                    Text(
                        "John Doe",
                        style = MaterialTheme.typography.bodyMedium.copy(fontWeight = FontWeight.Bold)
                    )
                    Spacer(Modifier.height(SpacingSmall))
                    Text("johndoe@mail.com", style = MaterialTheme.typography.bodySmall)
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
