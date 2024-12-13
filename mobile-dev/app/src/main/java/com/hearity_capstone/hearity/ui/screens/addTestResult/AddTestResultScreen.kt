package com.hearity_capstone.hearity.ui.screens.addTestResult

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.outlined.Subject
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
import androidx.compose.material3.HorizontalDivider
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
import androidx.compose.ui.text.input.KeyboardType
import androidx.navigation.NavHostController
import com.hearity_capstone.hearity.ui.common.AppButton
import com.hearity_capstone.hearity.ui.common.AppTextField
import com.hearity_capstone.hearity.ui.common.AppTopBar
import com.hearity_capstone.hearity.ui.common.SectionTitle
import com.hearity_capstone.hearity.ui.theme.PaddingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingItem
import com.hearity_capstone.hearity.ui.theme.SpacingSection
import com.hearity_capstone.hearity.ui.theme.SpacingSectionLarge
import com.hearity_capstone.hearity.util.DateUtils
import java.time.LocalDate


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTestResultScreen(navController: NavHostController, modifier: Modifier = Modifier) {
    val doctorName = remember { mutableStateOf("") }
    val hospitalName = remember { mutableStateOf("") }
    val selectedDate = remember { mutableStateOf<LocalDate>(LocalDate.now()) }

    val left500Hz = remember { mutableStateOf("0") }
    val left1000Hz = remember { mutableStateOf("0") }
    val left2000Hz = remember { mutableStateOf("0") }
    val left4000Hz = remember { mutableStateOf("0") }

    val right500Hz = remember { mutableStateOf("0") }
    val right1000Hz = remember { mutableStateOf("0") }
    val right2000Hz = remember { mutableStateOf("0") }
    val right4000Hz = remember { mutableStateOf("0") }

    val AS = remember { mutableStateOf("0") }
    val AD = remember { mutableStateOf("0") }

    val openDialog = remember { mutableStateOf(false) }

    val date = LocalDate.now()

    val datePickerState = remember {
        DatePickerState(
            yearRange = (date.year - 5)..(date.year + 5),
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
                .imePadding()
                .fillMaxSize()
                .padding(it)
                .padding(horizontal = PaddingMedium),
            contentAlignment = Alignment.Center
        ) {
            Column(Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState())) {

                // Doctor name input
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

                // Hospital name input
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

                // Date Selector
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

                // Image input
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Outlined.Image,
                        contentDescription = null,
                    )
                    Spacer(Modifier.width(SpacingSection))
                    AppButton(onClick = {}, label = "Select Test Result Image")
                }
                Spacer(Modifier.height(SpacingSectionLarge))

                // Hearing data section
                SectionTitle(title = "Hearing Data", icon = Icons.AutoMirrored.Outlined.Subject)
                Spacer(Modifier.height(SpacingSection))
                HorizontalDivider()
                Spacer(Modifier.height(SpacingItem))

                // Left
                Text("Left Ear", style = MaterialTheme.typography.titleSmall)
                Spacer(Modifier.height(SpacingItem))
                HearingDataInput(
                    hz500 = left500Hz.value,
                    hz1000 = left1000Hz.value,
                    hz2000 = left2000Hz.value,
                    hz4000 = left4000Hz.value,
                    onHz500Change = { v -> left500Hz.value = v },
                    onHz1000Change = { v -> left1000Hz.value = v },
                    onHz2000Change = { v -> left2000Hz.value = v },
                    onHz4000Change = { v -> left4000Hz.value = v }
                )
                Spacer(Modifier.height(SpacingSection))
                HorizontalDivider()
                Spacer(Modifier.height(SpacingSection))
                // Right
                Text("Right Ear", style = MaterialTheme.typography.titleSmall)
                Spacer(Modifier.height(SpacingItem))
                HearingDataInput(
                    hz500 = right500Hz.value,
                    hz1000 = right1000Hz.value,
                    hz2000 = right2000Hz.value,
                    hz4000 = right4000Hz.value,
                    onHz500Change = { v -> right500Hz.value = v },
                    onHz1000Change = { v -> right1000Hz.value = v },
                    onHz2000Change = { v -> right2000Hz.value = v },
                    onHz4000Change = { v -> right4000Hz.value = v }
                )
                Spacer(Modifier.height(SpacingSection))
                HorizontalDivider()
                Spacer(Modifier.height(SpacingSection))

                // AD AS
                Text("Audiological Diagnosis", style = MaterialTheme.typography.titleSmall)
                Spacer(Modifier.height(SpacingItem))
                HearingDataRow(
                    label1 = "AS",
                    label2 = "AD",
                    value1 = AS.value,
                    value2 = AD.value,
                    onValue1Change = { v -> AS.value = v },
                    onValue2Change = { v -> AD.value = v }
                )

            }
        }
    }
}

@Composable
fun HearingDataRow(
    value1: String,
    label1: String,
    onValue1Change: (String) -> Unit,
    value2: String,
    label2: String,
    onValue2Change: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = PaddingMedium),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AppTextField(
            modifier = Modifier.weight(1f),
            value = value1,
            label = label1,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = onValue1Change
        )
        Spacer(Modifier.width(SpacingItem))
        Text("dB", style = MaterialTheme.typography.bodyMedium)
        Spacer(Modifier.weight(0.25f))
        AppTextField(
            modifier = Modifier.weight(1f),
            value = value2,
            label = label2,
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
            onValueChange = onValue2Change
        )
        Spacer(Modifier.width(SpacingItem))
        Text("dB", style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun HearingDataInput(
    hz500: String,
    hz1000: String,
    hz2000: String,
    hz4000: String,
    onHz500Change: (String) -> Unit,
    onHz1000Change: (String) -> Unit,
    onHz2000Change: (String) -> Unit,
    onHz4000Change: (String) -> Unit
) {
    HearingDataRow(hz500, "500 Hz", onHz500Change, hz1000, "1000 Hz", onHz1000Change)
    Spacer(Modifier.height(SpacingItem))
    HearingDataRow(hz2000, "2000 Hz", onHz2000Change, hz4000, "4000 Hz", onHz4000Change)
}

