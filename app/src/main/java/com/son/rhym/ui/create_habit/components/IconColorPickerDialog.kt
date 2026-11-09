package com.son.rhym.ui.create_habit.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableLongStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.son.rhym.R
import com.son.rhym.ui.create_habit.habitIcons
import com.son.rhym.ui.theme.HabitColors

// 3 màu mặc định phổ biến
val defaultHabitColors = HabitColors.Primary

// Danh sách màu gợi ý sẵn cho bảng màu nhanh
val extendedColors = HabitColors.Extended

@Composable
fun IconColorPickerDialog(
    currentIcon: String,
    currentColor: Long,
    onDismiss: () -> Unit,
    onConfirm: (selectedIcon: String, selectedColor: Long) -> Unit
) {
    var selectedIcon by remember { mutableStateOf(currentIcon) }
    var selectedColor by remember { mutableLongStateOf(currentColor) }
    var showCustomColorPicker by remember { mutableStateOf(false) }

    val iconKeys = habitIcons.keys.toList()

    AlertDialog(
        onDismissRequest = onDismiss,
        containerColor = MaterialTheme.colorScheme.surfaceVariant,
        title = {
            Text(
                text = stringResource(R.string.icon_and_color),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold
            )
        },
        text = {
            Column(
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                // --- SECTION 1: CHỌN ICON ---
                Text(
                    text = stringResource(R.string.select_icon),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(4),
                    horizontalArrangement = Arrangement.spacedBy(10.dp),
                    verticalArrangement = Arrangement.spacedBy(10.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(140.dp)
                ) {
                    items(iconKeys) { key ->
                        val isSelected = (key == selectedIcon)
                        val iconVector = habitIcons[key] ?: return@items

                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(50.dp)
                                .clip(RoundedCornerShape(12.dp))
                                .background(
                                    if (isSelected) Color(selectedColor).copy(alpha = 0.2f)
                                    else MaterialTheme.colorScheme.surface
                                )
                                .border(
                                    width = if (isSelected) 2.dp else 1.dp,
                                    color = if (isSelected) Color(selectedColor)
                                    else MaterialTheme.colorScheme.outline.copy(alpha = 0.3f),
                                    shape = RoundedCornerShape(12.dp)
                                )
                                .clickable { selectedIcon = key }
                        ) {
                            Icon(
                                imageVector = iconVector,
                                contentDescription = key,
                                modifier = Modifier.size(24.dp),
                                tint = if (isSelected) Color(selectedColor)
                                else MaterialTheme.colorScheme.onSurface
                            )
                        }
                    }
                }

                // --- SECTION 2: CHỌN MÀU (3 MÀU MẶC ĐỊNH + 1 NÚT SPECTRUM CẦU VỒNG) ---
                Text(
                    text = stringResource(R.string.select_color),
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colorScheme.primary
                )

                Row(
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp)
                ) {
                    // 3 hình tròn màu mặc định
                    defaultHabitColors.forEach { colorHex ->
                        val isSelected = (colorHex == selectedColor)
                        Box(
                            contentAlignment = Alignment.Center,
                            modifier = Modifier
                                .size(44.dp)
                                .clip(CircleShape)
                                .background(Color(colorHex))
                                .clickable { selectedColor = colorHex }
                        ) {
                            if (isSelected) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = "Selected",
                                    tint = Color.White,
                                    modifier = Modifier.size(22.dp)
                                )
                            }
                        }
                    }

                    // Nút thứ 4: Mở bảng chọn gam màu (Luôn giữ hình tròn biểu tượng Palette)
                    val isCustomColorSelected = selectedColor !in defaultHabitColors
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .size(44.dp)
                            .clip(CircleShape)
                            .background(MaterialTheme.colorScheme.surface)
                            .border(
                                width = if (isCustomColorSelected) 2.dp else 1.dp,
                                color = if (isCustomColorSelected) Color(selectedColor)
                                else MaterialTheme.colorScheme.outline,
                                shape = CircleShape
                            )
                            .clickable { showCustomColorPicker = true }
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.Palette,
                            contentDescription = "Custom Color Palette",
                            tint = if (isCustomColorSelected) Color(selectedColor)
                            else MaterialTheme.colorScheme.onSurfaceVariant,
                            modifier = Modifier.size(22.dp)
                        )
                    }
                }
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onConfirm(selectedIcon, selectedColor)
                }
            ) {
                Text(stringResource(R.string.save), fontWeight = FontWeight.Bold)
            }
        },
        dismissButton = {
            TextButton(onClick = onDismiss) {
                Text(stringResource(R.string.cancel))
            }
        }
    )

    // Dialog chọn màu tùy chỉnh: Có Thanh Cầu Vồng (Rainbow Hue Spectrum Slider) kéo vuốt tay
    if (showCustomColorPicker) {
        var tempColor by remember { mutableStateOf(Color(selectedColor)) }

        AlertDialog(
            onDismissRequest = { showCustomColorPicker = false },
            containerColor = MaterialTheme.colorScheme.surfaceVariant,
            title = {
                Text(
                    text = stringResource(R.string.pick_custom_color),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            },
            text = {
                Column(
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    modifier = Modifier.fillMaxWidth()
                ) {
                    // Preview màu đang kéo chọn
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.spacedBy(12.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Box(
                            modifier = Modifier
                                .size(48.dp)
                                .clip(CircleShape)
                                .background(tempColor)
                                .border(2.dp, Color.White, CircleShape)
                        )
                        Text(
                            text = stringResource(R.string.drag_hue_slider),
                            fontSize = 14.sp,
                            color = MaterialTheme.colorScheme.onSurfaceVariant
                        )
                    }

                    // Thanh Dải Màu Cầu Vồng (Hue Spectrum Slider)
                    RainbowHueSlider(
                        onColorSelected = { color ->
                            tempColor = color
                        }
                    )

                    // Bảng màu nhanh bổ sung
                    Text(
                        text = stringResource(R.string.quick_palette),
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.primary
                    )

                    LazyVerticalGrid(
                        columns = GridCells.Fixed(4),
                        horizontalArrangement = Arrangement.spacedBy(8.dp),
                        verticalArrangement = Arrangement.spacedBy(8.dp),
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        items(extendedColors) { colorHex ->
                            val isSelected = (Color(colorHex) == tempColor)
                            Box(
                                contentAlignment = Alignment.Center,
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(CircleShape)
                                    .background(Color(colorHex))
                                    .clickable { tempColor = Color(colorHex) }
                            ) {
                                if (isSelected) {
                                    Icon(
                                        imageVector = Icons.Default.Check,
                                        contentDescription = "Selected",
                                        tint = Color.White,
                                        modifier = Modifier.size(18.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        val argb = tempColor.toArgb()
                        selectedColor = argb.toLong() and 0xFFFFFFFFL
                        showCustomColorPicker = false
                    }
                ) {
                    Text(stringResource(R.string.select), fontWeight = FontWeight.Bold)
                }
            },
            dismissButton = {
                TextButton(onClick = { showCustomColorPicker = false }) {
                    Text(stringResource(R.string.cancel))
                }
            }
        )
    }
}

// Composable Thanh Trượt Cầu Vồng (Rainbow Spectrum Slider) kéo/di ngón tay để đổi gam màu
@Composable
fun RainbowHueSlider(
    onColorSelected: (Color) -> Unit
) {
    val rainbowColors = remember {
        listOf(
            Color.Red, Color.Yellow, Color.Green,
            Color.Cyan, Color.Blue, Color.Magenta, Color.Red
        )
    }
    var currentHue by remember { mutableFloatStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(36.dp)
            .clip(RoundedCornerShape(18.dp))
            .background(Brush.horizontalGradient(rainbowColors))
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.outline,
                shape = RoundedCornerShape(18.dp)
            )
            .pointerInput(Unit) {
                detectTapGestures { offset ->
                    val progress = (offset.x / size.width).coerceIn(0f, 1f)
                    currentHue = progress * 360f
                    val hsvColor = Color.hsv(currentHue, 0.85f, 0.95f)
                    onColorSelected(hsvColor)
                }
            }
            .pointerInput(Unit) {
                detectDragGestures { change, _ ->
                    val progress = (change.position.x / size.width).coerceIn(0f, 1f)
                    currentHue = progress * 360f
                    val hsvColor = Color.hsv(currentHue, 0.85f, 0.95f)
                    onColorSelected(hsvColor)
                }
            }
    )
}
