package com.son.rhym.ui.common.swipeable

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.son.rhym.R
import com.son.rhym.model.HabitItem
import com.son.rhym.ui.home.components.HabitCard

@Composable
fun SwipeActionContainer(
    habit: HabitItem,
    modifier: Modifier = Modifier,
    onToggleCompleted: (HabitItem) -> Unit = {},
    onStartActionTriggered: () -> Unit = {},
) {
    val radius = 16.dp

    SwipeActionButton(
        startAction = {
            Box(
                Modifier
                    .fillMaxSize()
                    .clip(RoundedCornerShape(radius, 0.dp, 0.dp, radius))
                    .background(Color(0xFF2E7D32))
                    .clickable { /* e.g. archive */ },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_flag),
                    contentDescription = null,
                    tint = Color.White
                )
            }
        },
        endAction = {
            Row(Modifier.fillMaxSize()) {
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .background(Color(0xFF005CAD))
                        .clickable { /* edit */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_edit),
                        contentDescription = "Edit",
                        tint = Color.White
                    )
                }
                Box(
                    Modifier
                        .weight(1f)
                        .fillMaxHeight()
                        .clip(RoundedCornerShape(0.dp, radius, radius, 0.dp))
                        .background(Color(0xFFC62828))
                        .clickable { /* delete */ },
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(R.drawable.ic_delete),
                        contentDescription = "Delete",
                        tint = Color.White
                    )
                }
            }

        }
    ) { offsetX ->

        val left by animateDpAsState(if (offsetX > 0f) 0.dp else radius, label = "left")
        val right by animateDpAsState(if (offsetX < 0f) 0.dp else radius, label = "right")

        val shape = RoundedCornerShape(
            topStart = left,
            bottomStart = left,
            topEnd = right,
            bottomEnd = right
        )

        HabitCard(
            habit,
            modifier,
            shape,
            onToggleCompleted
        )
    }
}
