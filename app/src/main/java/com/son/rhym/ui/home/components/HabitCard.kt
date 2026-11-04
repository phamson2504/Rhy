package com.son.rhym.ui.home.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.StrokeJoin
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.son.rhym.model.HabitItem

@Composable
fun HabitCard(
    habit: HabitItem,
    modifier: Modifier = Modifier,
    cornerShape: Shape = RoundedCornerShape(16.dp),
    onToggleCompleted: (HabitItem) -> Unit = {}
) {
    val colorScheme = MaterialTheme.colorScheme

    Card(
        modifier = modifier
            .fillMaxWidth()
            .border(1.dp, colorScheme.outline, cornerShape),
        shape = cornerShape,
        colors = CardDefaults.cardColors(
            containerColor = colorScheme.surface
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 0.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Accent
            Box(
                modifier = Modifier
                    .width(5.dp)
                    .height(36.dp)
                    .clip(RoundedCornerShape(3.dp))
                    .background(habit.accentColor)
            )

            Spacer(modifier = Modifier.width(14.dp))

            // Habit information
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = habit.title,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold,
                    color = colorScheme.onSurface
                )

                Spacer(modifier = Modifier.height(3.dp))

                Text(
                    text = habit.subtitle,
                    fontSize = 13.sp,
                    color = colorScheme.onSurfaceVariant
                )

                if (habit.progressPercent != null && habit.progressText != null) {
                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = habit.progressText,
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = colorScheme.primary
                    )
                }
            }

            // Right side
            if (habit.progressPercent != null && habit.progressText != null && !habit.isCompleted) {
                Spacer(modifier = Modifier.width(12.dp))

                CircularHabitProgress(
                    progress = habit.progressPercent
                )
            } else {
                Spacer(modifier = Modifier.width(12.dp))

                HabitCheckTarget(
                    isCompleted = habit.isCompleted,
                    onClick = { onToggleCompleted(habit) }
                )
            }
        }
    }
}

@Composable
fun CircularHabitProgress(
    progress: Float,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme
    val percentage = (progress * 100).toInt().coerceIn(0, 100)

    Box(
        modifier = modifier.size(40.dp),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = Modifier.fillMaxSize(),
            color = colorScheme.primaryContainer,
            trackColor = colorScheme.onPrimaryContainer,
            strokeWidth = 4.dp,
            strokeCap = StrokeCap.Round
        )

        Text(
            text = "$percentage%",
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            color = colorScheme.onSurface
        )
    }
}

@Composable
fun HabitCheckTarget(
    isCompleted: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val colorScheme = MaterialTheme.colorScheme

    if (isCompleted) {
        Box(
            modifier = modifier
                .size(28.dp)
                .clip(CircleShape)
                .background(colorScheme.primary)
                .clickable { onClick() },
            contentAlignment = Alignment.Center
        ) {
            CheckmarkIcon(
                modifier = Modifier.size(14.dp),
                color = Color.White
            )
        }
    } else {
        Box(
            modifier = modifier
                .size(28.dp)
                .clip(CircleShape)
                .border(2.5.dp, colorScheme.outline, CircleShape)
                .clickable { onClick() }
        )
    }
}

@Composable
fun CheckmarkIcon(
    modifier: Modifier = Modifier,
    color: Color = Color.White
) {
    Canvas(modifier = modifier) {
        val strokeWidth = 2.2.dp.toPx()

        val path = Path().apply {
            moveTo(size.width * 0.22f, size.height * 0.52f)
            lineTo(size.width * 0.42f, size.height * 0.72f)
            lineTo(size.width * 0.78f, size.height * 0.32f)
        }

        drawPath(
            path = path,
            color = color,
            style = Stroke(
                width = strokeWidth,
                cap = StrokeCap.Round,
                join = StrokeJoin.Round
            )
        )
    }
}
