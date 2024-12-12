package com.hearity_capstone.hearity.ui.common.audiometryGraph

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.hearity_capstone.hearity.data.model.testResult.EarSide

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EarDropdown(
    modifier: Modifier = Modifier,
    onSelected: (EarSide) -> Unit,
    selected: EarSide = EarSide.LEFT,
) {
    val isExpanded = remember { mutableStateOf(false) }

    ExposedDropdownMenuBox(
        expanded = isExpanded.value,
        onExpandedChange = { isExpanded.value = it },
        modifier = modifier
            .height(44.dp)
            .width(125.dp)
    ) {
        TextField(
            value = when (selected) {
                EarSide.LEFT -> "Left"
                EarSide.RIGHT -> "Right"
                else -> "Both"
            },
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded.value)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(
                focusedContainerColor = Color.Transparent,
                unfocusedContainerColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            textStyle = MaterialTheme.typography.labelSmall.copy(textAlign = TextAlign.Center),
            modifier = Modifier
                .menuAnchor()
        )
        ExposedDropdownMenu(
            expanded = isExpanded.value,
            onDismissRequest = { isExpanded.value = false },
            modifier = Modifier.background(
                color = MaterialTheme.colorScheme.surfaceBright,
                shape = MaterialTheme.shapes.medium
            )
        ) {
            EarSide.entries.map { option ->
                DropdownMenuItem(
                    onClick = {
                        isExpanded.value = false
                        onSelected(option)
                    },
                    text = {
                        Text(
                            text = when (option) {
                                EarSide.LEFT -> "Left"
                                EarSide.RIGHT -> "Right"
                                else -> "Both"
                            },
                            style = MaterialTheme.typography.labelSmall.copy(
                                color = MaterialTheme.colorScheme.onSurfaceVariant
                            )
                        )
                    }
                )
            }
        }
    }
}

