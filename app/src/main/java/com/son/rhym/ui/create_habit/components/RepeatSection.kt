package com.son.rhym.ui.create_habit.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Composable
fun RepeatSection() {
    Column(
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.padding(top = 18.dp)
    ) {
        Text(
            text = "REPEAT",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colorScheme.onBackground,
            letterSpacing = 0.5.sp
        )
        Card(
            shape = RoundedCornerShape(16.dp),
            border = BorderStroke(
                1.dp,
                MaterialTheme.colorScheme.outline
            ),
            colors = CardDefaults.cardColors(
                containerColor = MaterialTheme.colorScheme.surface
            ),
            modifier = Modifier.fillMaxWidth()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Image(
                    painter = painterResource(R.drawable.ic_repeat),
                    contentDescription = "Repeat",
                    modifier = Modifier.size(24.dp),
                    colorFilter = ColorFilter.tint(
                        MaterialTheme.colorScheme.onBackground
                    )
                )

                Column(
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                ) {
                    Text(
                        text = "Every day",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Medium,
                        color = MaterialTheme.colorScheme.onBackground
                    )

                    Text(
                        text = "tap to change schedule",
                        fontSize = 12.sp,
                        color = Color.Gray
                    )
                }

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
    }
}
