package com.hearity_capstone.hearity.ui.screens.profile

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import com.hearity_capstone.hearity.ui.common.AppTextField
import com.hearity_capstone.hearity.ui.theme.SpacingItem

@Composable
fun EditProfileDialog(
    onCancel: () -> Unit,
    onSave: () -> Unit
) {
    var name by remember { mutableStateOf("John Doe") }
    var email by remember { mutableStateOf("johndoe@mail.com") }

    AlertDialog(
        onDismissRequest = { },
        shape = MaterialTheme.shapes.extraLarge,
        title = {
            Text(
                text = "Edit Profile",
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AppTextField(
                    value = name,
                    label = "Name",
                    onValueChange = { name = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(SpacingItem))
                AppTextField(
                    value = email,
                    label = "Email",
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth()
                )
                Spacer(Modifier.height(SpacingItem))
            }
        },
        dismissButton = {
            TextButton(
                onClick = { onCancel() }
            ) { Text("Cancel") }
        },
        confirmButton = {
            Button(onClick = { onSave() }
            ) { Text("Save") }
        },
    )
}
