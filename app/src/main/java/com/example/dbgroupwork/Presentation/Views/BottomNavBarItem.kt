package com.example.dbgroupwork.Presentation.Views

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Place
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavBarItem (val route: String, val title: String, val icon: ImageVector){
    data object Home : BottomNavBarItem("home", "Create Plan!", Icons.Default.Home)
    data object Profile : BottomNavBarItem("map", "Gyms Nearby", Icons.Default.Place)
    data object Community : BottomNavBarItem("community", "Community", Icons.Default.Face)
    data object Settings : BottomNavBarItem("settings", "Settings", Icons.Default.Settings)
}