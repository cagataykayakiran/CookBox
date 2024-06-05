package com.example.recipeapp.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.recipeapp.presentation.ui.theme.MainColorPrimary
import com.example.recipeapp.presentation.ui.theme.MainColorSecondary
import com.example.recipeapp.presentation.ui.theme.futuraSansFamily
import com.example.recipeapp.util.Screen


data class BottomNavigationItem(
    val title: String,
    val route: Screen,
    val selectedIcon: ImageVector,
    val unselectedIcon: ImageVector,
)

@Composable
fun AppBottomBar(navController: NavController) {
    val items = listOf(
        BottomNavigationItem(
            title = "Home",
            selectedIcon = Icons.Filled.Home,
            unselectedIcon = Icons.Outlined.Home,
            route = Screen.MainScreen
        ),
        BottomNavigationItem(
            title = "Favorites",
            selectedIcon = Icons.Filled.Favorite,
            unselectedIcon = Icons.Outlined.Favorite,
            route = Screen.FavoriteScreen
        )
    )
    val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
    var selectedIndex by rememberSaveable { mutableIntStateOf(0) }
    selectedIndex = items.indexOfFirst { it.route.route == currentRoute }
    NavigationBar(
        containerColor = Color.White
    ) {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = {
                    selectedIndex = index
                    navController.navigate(item.route.route) {
                        launchSingleTop = true
                    }
                },
                icon = {
                    Icon(
                        imageVector = if (index == selectedIndex) {
                            item.selectedIcon
                        } else item.unselectedIcon, contentDescription = null
                    )
                },
                label = {
                    if (selectedIndex == index) {
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