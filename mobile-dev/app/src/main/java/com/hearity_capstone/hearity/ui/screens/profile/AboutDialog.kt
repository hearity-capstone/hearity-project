package com.hearity_capstone.hearity.ui.screens.profile


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.AlertDialog
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
                Text(
                    "HeaRity v0.1",
                    style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(SpacingItem))
                Text("Powered By Bangkit Academy 2024", color = MaterialTheme.colorScheme.onSurfaceVariant)
                Spacer(Modifier.height(SpacingSection))
                Text("Developed by:", style = MaterialTheme.typography.bodyLarge,
                    fontWeight = FontWeight.Bold
                )
                Spacer(Modifier.height(SpacingItem))
                Text(
                    "Gusti Ayu Putu Erika Erlina\n" +
                            "Agnes Sura Pati Sinaga\n" +
                            "Aryasaty Kirana Tungga Maheswari\n" +
                            "Nabila Naurotul Ummah\n" +
                            "Naufal Adhi Ramadhan\n" +
                            "Eko Kurniawan\n" +
                            "Rangga Felicia Fulfian\n",
                    textAlign = TextAlign.Center,
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