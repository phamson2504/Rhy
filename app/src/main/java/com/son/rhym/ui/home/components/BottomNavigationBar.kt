package com.son.rhym.ui.home.components

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.son.rhym.R

@Composable
fun BottomNavigationBar() {
    var selectedItem by remember { mutableIntStateOf(0) }

    val selectedColor = MaterialTheme.colorScheme.primary
    val selectedBackground = MaterialTheme.colorScheme.primaryContainer
    val barBackground = MaterialTheme.colorScheme.surface

    NavigationBar(
        containerColor = barBackground,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        NavigationBarItem(
            selected = selectedItem == 0,
            onClick = { selectedItem = 0 },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_tasks),
                    contentDescription = "Todo"
                )
            },
            label = { Text("Todo") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                indicatorColor = selectedBackground,
            )
        )

        NavigationBarItem(
            selected = selectedItem == 1,
            onClick = { selectedItem = 1 },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_chart),
                    contentDescription = "Statistics"
                )
            },
            label = { Text("Statistics") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                indicatorColor = selectedBackground,
            )
        )

        NavigationBarItem(
            selected = selectedItem == 2,
            onClick = { selectedItem = 2 },
            icon = {
                Icon(
                    painter = painterResource(R.drawable.ic_setting),
                    contentDescription = "Setting"
                )
            },
            label = { Text("Setting") },
            colors = NavigationBarItemDefaults.colors(
                unselectedIconColor = MaterialTheme.colorScheme.onPrimary,
                unselectedTextColor = MaterialTheme.colorScheme.onPrimary,
                selectedIconColor = selectedColor,
                selectedTextColor = selectedColor,
                indicatorColor = selectedBackground,
            )
        )
    }
}
