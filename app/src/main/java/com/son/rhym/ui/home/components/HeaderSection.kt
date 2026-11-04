package com.son.rhym.ui.home.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.son.rhym.R
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale

@Composable
fun HeaderSection(
) {
    val currentDate = LocalDate.now()

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(34.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Row(
            modifier = Modifier.weight(1f),
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.ic_menu),
                contentDescription = "Menu",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onBackground
                )
            )

            Spacer(Modifier.width(8.dp))

            Text(
                text = currentDate.format(
                    DateTimeFormatter.ofPattern(
                        "EEE, MMM dd, yyyy",
                        Locale.ENGLISH
                    )
                ),
                color = MaterialTheme.colorScheme.primary,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                maxLines = 1
            )
        }

        // Right: Filter + Calendar
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {

            Image(
                painter = painterResource(R.drawable.ic_filter),
                contentDescription = "Filter",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onBackground
                )
            )
            Spacer(Modifier.width(8.dp))
            Image(
                painter = painterResource(R.drawable.ic_calendar),
                contentDescription = "Calendar",
                modifier = Modifier.size(24.dp),
                colorFilter = ColorFilter.tint(
                    MaterialTheme.colorScheme.onBackground
                )
            )

        }
    }
}
