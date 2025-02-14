package com.example.dbgroupwork.Presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavBarItem (val route: String, val title: String, val icon: ImageVector){
    object Home : BottomNavBarItem("home", "Create Plan!", Icons.Default.Home)
    object Profile : BottomNavBarItem("map", "Gyms Nearby", Icons.Default.Place)
    object Settings : BottomNavBarItem("settings", "Settings", Icons.Default.Settings)
}