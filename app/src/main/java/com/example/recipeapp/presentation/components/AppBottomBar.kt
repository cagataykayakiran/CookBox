package com.example.recipeapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import com.example.recipeapp.presentation.ui.theme.MainColorSecondary
import com.example.recipeapp.presentation.ui.theme.futuraSansFamily


data class BottomNavigationItem(
    val title: String,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun AppBottomBar() {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
        ),
        BottomNavigationItem(
            title = "Favorites",
            selectedIcon = Icons.Filled.Email,
            unselectedIcon = Icons.Outlined.Email,
        )
    )
    var selectedItemByIndex by rememberSaveable { mutableIntStateOf(0) }
    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItemByIndex == index,
                onClick = { selectedItemByIndex = index },
                icon = {
                    Icon(
                        imageVector = if (index == selectedItemByIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon, contentDescription = null
                    )
                },
                label = {
                    if (selectedItemByIndex == index) {
                        Text(
                            text = item.title,
                            fontSize = 12.sp,
                            fontFamily = futuraSansFamily,
                            fontWeight = FontWeight.Bold
                        )
                    }
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MainColorPrimary,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = MainColorSecondary
                )
            )
        }
    }
}