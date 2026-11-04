package com.son.rhym.components.home

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@Composable
fun WeeklyCalendarSection(
    selectedDate: LocalDate,
    modifier: Modifier = Modifier,
    onDateSelected: (LocalDate) -> Unit
) {

    val dates = remember(selectedDate) {
        (-30L..30L).map { selectedDate.plusDays(it) }
    }
    val listState = rememberLazyListState(
        initialFirstVisibleItemIndex = 28
    )

    LaunchedEffect(selectedDate) {
        listState.animateScrollToItem(28)
    }


    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        state = listState,
        contentPadding = PaddingValues(horizontal = 15.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        itemsIndexed(
            items = dates,
            key = { _, date -> date.toString() }
        ) { _, date ->
            val selected = date == selectedDate
            val isDateNow = date == LocalDate.now()
            Column(
                modifier = modifier
                    .width(60.dp)
                    .clip(
                        RoundedCornerShape(18.dp),
                    )
                    .background(
                        if (selected) {
                            MaterialTheme.colorScheme.primary
                        } else {
                            MaterialTheme.colorScheme.surfaceVariant
                        }
                    )
                    .clickable {
                        onDateSelected(date)
                    }
                    .padding(vertical = 12.dp),

                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = date.dayOfWeek.getDisplayName(
                        TextStyle.SHORT,
                        Locale.ENGLISH
                    ).uppercase(Locale.ENGLISH),
                    color = if (selected) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 11.sp
                )

                Text(
                    text = date.dayOfMonth.toString(),
                    color = if (selected) {
                        MaterialTheme.colorScheme.onPrimary
                    } else {
                        MaterialTheme.colorScheme.onSurfaceVariant
                    },
                    fontWeight = FontWeight.Bold,
                    fontSize = 22.sp,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        textDecoration = if (isDateNow) {
                            TextDecoration.Underline
                        } else {
                            TextDecoration.None
                        }
                    )
                )
            }
        }
    }

}