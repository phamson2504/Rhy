package com.son.rhym.ui.create_habit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalMinimumInteractiveComponentSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
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
import androidx.compose.ui.unit.sp
import com.son.rhym.R
import java.time.LocalTime

@Composable
fun ReminderSection(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    reminderTimes: List<LocalTime>,
    onReminderTimesChange: (List<LocalTime>) -> Unit
) {

    var showTimePicker by remember {
        mutableStateOf(false)
    }

    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .background(MaterialTheme.colorScheme.background),

            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(R.drawable.ic_notifications),
                contentDescription = "Notifications",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.primary
                )
            )

            Text(
                text = "Get reminders",
                fontSize = 15.sp,
                fontWeight = FontWeight.Medium,
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 12.dp)
            )


            CompositionLocalProvider(
                LocalMinimumInteractiveComponentSize provides 0.dp
            ) {
                Switch(
                    checked = isChecked,
                    onCheckedChange = onCheckedChange
                )
            }
        }
        if (isChecked) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable { showTimePicker = true }
                    .padding(vertical = 12.dp, horizontal = 16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {

                Text(
                    text = "Add Reminder",
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(start = 36.dp)
                )

                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "null"
                )
            }

            reminderTimes.forEach { time ->
                ReminderTimeItem(
                    time = time,
                    onDelete =  {
                        onReminderTimesChange(
                            reminderTimes - time
                        )
                    }
                )
            }
        }
    }

    ReminderTimePicker(
        showDialog = showTimePicker,
        onDismiss = {
            showTimePicker = false
        },
        onConfirm = { time ->
            if (time !in reminderTimes) {
                onReminderTimesChange(
                    (reminderTimes + time).sorted()
                )
            }

            showTimePicker = false
        }
    )

    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outline
    )
}
