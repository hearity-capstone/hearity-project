package com.hearity_capstone.hearity.ui.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.R

@Composable
fun AvatarSelectorDialog(
    onAvatarSelected: (Int) -> Unit,
    onCancel: () -> Unit = {}
) {

    val avatars = listOf(
        R.drawable.avatar_dylan,
        R.drawable.avatar_andrea,
        R.drawable.avatar_jade,
        R.drawable.avatar_ryker,
        R.drawable.avatar_shopia,
        R.drawable.avatar_vivian,
        R.drawable.avatar_jessie,
        R.drawable.avatar_maximilan,
    )

    AlertDialog(
        onDismissRequest = { },
        shape = MaterialTheme.shapes.extraLarge,
        title = {
            Text(
                text = "Select Your Avatar",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            LazyVerticalGrid(
                columns = GridCells.Fixed(4),
                content = {
                    items(avatars) { avatar ->
                        Image(
                            painter = painterResource(id = avatar),
                            contentDescription = null,
                            modifier = Modifier
                                .size(64.dp)
                                .clip(CircleShape)
                                .clickable { onAvatarSelected(avatar) }
                                .padding(8.dp)
                        )
                    }
                }
            )
        },
        confirmButton = {},
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
