package com.son.rhym.core.navigation

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.res.painterResource
import com.son.rhym.R

@Composable
fun BottomNavigationBar(
    selectedTab: Int = 0,
    onTabSelected: (Int) -> Unit = {},
) {
    val selectedColor = MaterialTheme.colorScheme.primary
    val selectedBackground = MaterialTheme.colorScheme.primaryContainer
    val barBackground = MaterialTheme.colorScheme.surface

    NavigationBar(
        containerColor = barBackground,
        contentColor = MaterialTheme.colorScheme.onSurface
    ) {
        NavigationBarItem(
            selected = selectedTab == 0,
            onClick = { onTabSelected(0) },
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
            selected = selectedTab == 1,
            onClick = { onTabSelected(1) },
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
            selected = selectedTab == 2,
            onClick = { onTabSelected(2) },
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
