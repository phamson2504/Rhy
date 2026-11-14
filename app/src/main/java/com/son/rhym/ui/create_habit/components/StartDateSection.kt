package com.son.rhym.ui.create_habit.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.son.rhym.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@Composable
fun StartDateSection(
    startDate: LocalDate,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .clickable {
                onClick()
            },
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            Image(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = "Start Date",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.primary
                )
            )
            Column {
                Text(
                    text = "Start date",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Medium
                )
            }
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = startDate.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)),
                fontSize = 14.sp,
                color = Color.Gray,
                modifier = Modifier.padding(end = 4.dp)
            )
            Image(
                painter = painterResource(R.drawable.ic_right_arrow),
                contentDescription = "Right Arrow",
                modifier = Modifier.size(20.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onBackground
                )
            )
        }
    }

    HorizontalDivider(
        thickness = 1.dp,
        color = MaterialTheme.colorScheme.outline
    )
}
