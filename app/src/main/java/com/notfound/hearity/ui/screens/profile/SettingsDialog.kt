package com.notfound.hearity.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.DarkMode
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.notfound.hearity.ui.theme.IconSizeMedium
import com.notfound.hearity.ui.theme.SpacingItem
import com.notfound.hearity.ui.theme.SpacingSmall

@Composable
fun SettingsDialog(
    onCancel: () -> Unit = {},
    onSave: () -> Unit = {}
) {
    var notificationEnabled by remember { mutableStateOf(true) }
    var darkModeEnabled by remember { mutableStateOf(false) }

    AlertDialog(
        onDismissRequest = { },
        shape = MaterialTheme.shapes.extraLarge,
        title = {
            Text(
                text = "Settings",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = SpacingSmall),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.Notifications,
                            contentDescription = "Notification",
                            modifier = Modifier.size(IconSizeMedium)
                        )
                        Spacer(Modifier.width(SpacingItem))
                        Text("Notification")
                    }
                    Switch(
                        checked = notificationEnabled,
                        onCheckedChange = { notificationEnabled = it }
                    )
                }
                Row(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = SpacingSmall),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            imageVector = Icons.Outlined.DarkMode,
                            contentDescription = "Dark Mode",
                            modifier = Modifier.size(
                                IconSizeMedium
                            )
                        )
                        Spacer(Modifier.width(SpacingItem))
                        Text("Dark Mode")
                    }
                    Switch(
                        checked = darkModeEnabled,
                        onCheckedChange = { darkModeEnabled = it }
                    )
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onSave()
                }
            ) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(
                onClick = {
                    onCancel()
                }
            ) {
                Text("Cancel")
            }
        }
    )
}

