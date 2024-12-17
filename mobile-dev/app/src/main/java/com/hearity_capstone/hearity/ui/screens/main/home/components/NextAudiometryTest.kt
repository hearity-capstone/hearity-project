package com.hearity_capstone.hearity.ui.screens.main.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CalendarLocale
import androidx.compose.material3.CardColors
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DatePickerState
import androidx.compose.material3.DisplayMode
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import com.hearity_capstone.hearity.R
import com.hearity_capstone.hearity.ui.common.AppCard
import com.hearity_capstone.hearity.ui.common.AppCardSize
import com.hearity_capstone.hearity.ui.theme.IconContainerSizeMedium
import com.hearity_capstone.hearity.ui.theme.IconSizeMedium
import com.hearity_capstone.hearity.ui.theme.SpacingMedium
import com.hearity_capstone.hearity.ui.theme.SpacingSmall
import com.hearity_capstone.hearity.util.DateUtils
import com.hearity_capstone.hearity.viewModel.ReminderViewModel
import org.koin.androidx.compose.koinViewModel
import java.time.LocalDate

@Composable
fun NextAudiometryTest(modifier: Modifier = Modifier) {
    val reminderViewModel: ReminderViewModel = koinViewModel()


    val reminderDate by reminderViewModel.reminderDate.collectAsState()
    var isOpenDialog by remember { mutableStateOf<Boolean>(false) }

    val reminderDateValue = reminderDate

    if (isOpenDialog) {
        DateDialog(
            onDismiss = { isOpenDialog = !isOpenDialog },
            onDateSelected = { date -> reminderViewModel.saveReminder(date) }
        )
    }
    AppCard(
        modifier = modifier,
        size = AppCardSize.LARGE,
        onClick = {
            isOpenDialog = true
        },
        colors = CardColors(
            containerColor = MaterialTheme.colorScheme.secondaryContainer,
            contentColor = MaterialTheme.colorScheme.onSurfaceVariant,
            disabledContainerColor = MaterialTheme.colorScheme.secondaryContainer,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Icon
            Box(
                modifier = Modifier
                    .size(IconContainerSizeMedium)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary.copy(alpha = 0.75f)),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_event_filled),
                    contentDescription = "Next Audiometry Test Icon",
                    modifier = Modifier.size(IconSizeMedium),
                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary),
                    alignment = Alignment.Center
                )
            }
            Spacer(Modifier.height(SpacingMedium))
            if (reminderDateValue == null) NoReminderContent() else ReminderContent(
                reminderDateValue
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DateDialog(
    onDateSelected: (LocalDate) -> Unit,
    onDismiss: () -> Unit,
) {
    val currentDate = LocalDate.now()

    val datePickerState = remember {
        DatePickerState(
            yearRange = (currentDate.year - 5)..(currentDate.year + 5),
            initialDisplayMode = DisplayMode.Picker,
            locale = CalendarLocale.ENGLISH,
        )
    }

    DatePickerDialog(
        onDismissRequest = { onDismiss() },
        confirmButton = {
            TextButton(onClick = {
                val selectedDate = datePickerState.selectedDateMillis?.let {
                    LocalDate.ofEpochDay(it / 86400000)
                } ?: currentDate
                onDateSelected(selectedDate)
                onDismiss()
            }) {
                Text("Save")
            }
        },
        dismissButton = {
            TextButton(onClick = { onDismiss() }) {
                Text("Cancel")
            }
        }
    ) {
        DatePicker(
            state = datePickerState
        )
    }
}


@Composable
fun NoReminderContent() {
    Column(
        Modifier.fillMaxHeight(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            "Reminder not set",
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
    }
}

@Composable
fun ReminderContent(
    reminderDate: LocalDate,
) {
    val daysBetween = DateUtils.calculateDaysBetween(LocalDate.now(), reminderDate)

    Column(
        modifier = Modifier.fillMaxHeight(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = when {
                    daysBetween < 0L -> "0"
                    else -> daysBetween.toString()
                },
                style = MaterialTheme.typography.displayMedium
                    .copy(
                        color = MaterialTheme.colorScheme.primary,
                        fontWeight = FontWeight.Bold
                    )
            )

            Spacer(Modifier.width(SpacingSmall))
            Text(
                "d", style = MaterialTheme.typography.bodyLarge.copy(
                    color = MaterialTheme.colorScheme.primary
                )
            )
        }
        Spacer(Modifier.height(SpacingMedium))
        Text(
            text = when {
                daysBetween == 0L -> "Your Test is Scheduled Today!"
                daysBetween > 0 -> "Until Next Audiometry Test"
                else -> "Test passed ${-daysBetween} day(s) ago"
            },
            style = MaterialTheme.typography.labelMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
    }
}