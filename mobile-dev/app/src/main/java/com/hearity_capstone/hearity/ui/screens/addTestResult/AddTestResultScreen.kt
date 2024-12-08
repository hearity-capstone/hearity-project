package com.hearity_capstone.hearity.ui.screens.addTestResult

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Apartment
import androidx.compose.material.icons.outlined.Check
import androidx.compose.material.icons.outlined.Event
import androidx.compose.material.icons.outlined.Image
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.ui.common.AppButton
import com.hearity_capstone.hearity.ui.common.AppTextField
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.util.DateUtils
import java.time.LocalDate

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTestResultScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val doctorName = remember { mutableStateOf("") }
    val hospitalName = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf<LocalDate>(LocalDate.now()) }

    val openDialog = remember { mutableStateOf(false) }

    val date = LocalDate.now()

    val datePickerState = remember {
        DatePickerState(
            yearRange = (date.year - 10)..(date.year + 10),
            initialDisplayMode = DisplayMode.Picker,
            locale = CalendarLocale.ENGLISH,
        )
    }

    if (openDialog.value) {
        DatePickerDialog(
            onDismissRequest = { openDialog.value = false },
            confirmButton = {
                TextButton(onClick = {
                    openDialog.value = false
                    selectedDate.value = datePickerState.selectedDateMillis.let {
                        LocalDate.ofEpochDay(
                            it?.div(86400000) ?: LocalDate.now().toEpochDay()
                        )
                    }

                }) {
                    Text("OK")
                }
            },
            dismissButton = { },
        ) {
            DatePicker(state = datePickerState)
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(navController = navController, title = "Add Test Result", action = {
                IconButton(onClick = {}) {
                    Icon(
                        imageVector = Icons.Outlined.Check,
                        contentDescription = null,
                    )

                }
            })
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .padding(horizontal = PaddingMedium),
            contentAlignment = Alignment.Center
        ) {
            Column(Modifier.fillMaxWidth()) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Person,
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(SpacingSection))
                    AppTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = doctorName.value,
                        label = "Doctor Name",
                        onValueChange = { v -> doctorName.value = v })
                }
                Spacer(Modifier.height(SpacingItem))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Apartment,
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(SpacingSection))
                    AppTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = hospitalName.value,
                        label = "Hospital or clinic name",
                        onValueChange = { v -> hospitalName.value = v })
                }
                Spacer(Modifier.height(SpacingSection))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Event,
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(SpacingSection))
                    AppTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable { openDialog.value = !openDialog.value },
                        enable = false,
                        value = selectedDate.value.let { v -> DateUtils.formatLocalDate(v) },
                        label = "Date",
                        readOnly = true,
                        onValueChange = {},
                        colors = TextFieldDefaults.colors(
                            disabledContainerColor = Color.Transparent,
                            disabledTextColor = MaterialTheme.colorScheme.onSurface,
                            disabledLabelColor = MaterialTheme.colorScheme.onSurface,
                            disabledPlaceholderColor = MaterialTheme.colorScheme.onSurface,
                        )
                    )
                }
                Spacer(Modifier.height(SpacingSection))

                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Image,
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(SpacingSection))
                    AppButton(onClick = {}, label = "Select Test Result Image")
                }
            }
        }
    }
}
