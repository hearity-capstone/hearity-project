package com.hearity_capstone.hearity.ui.screens.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.ui.theme.IconSizeLarge
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection


@Composable
fun AboutDialog(
    onCancel: () -> Unit = {},
) {
    AlertDialog(
        onDismissRequest = { onCancel() },
        icon = {
            Icon(
                painter = painterResource(R.drawable.ic_info_outline),
                contentDescription = "Info Icon",
                modifier = Modifier.size(IconSizeLarge)
            )
        },
        shape = MaterialTheme.shapes.extraLarge,
        title = {
            Text(
                text = "About HeaRity",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
                HorizontalDivider()
                Spacer(Modifier.height(SpacingSection))
                Text(
                    "HeaRity v0.1",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(SpacingItem))
                Text("Powered By Bangkit Academy 2024", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(SpacingSection))
                Text(
                    "Developed by: Team C242-PS043", style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(SpacingItem))
                Text(
                    "Gusti Ayu Putu Erika Erlina - ML \n" +
                            "Agnes Sura Pati Sinaga - ML\n" +
                            "Aryasaty Kirana Tungga Maheswari - ML\n" +
                            "Nabila Naurotul Ummah - CC \n" +
                            "Naufal Adhi Ramadhan - CC\n" +
                            "Eko Kurniawan - MD\n" +
                            "Rangga Felicia Fulfian - MD\n",
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )

            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onCancel()
                }
            ) {
                Text("Close")
            }
        },
    )
}