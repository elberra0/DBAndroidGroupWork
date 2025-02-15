package com.example.dbgroupwork

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.dbgroupwork.Presentation.BottomNavBarItem
import com.example.dbgroupwork.Presentation.Theme.DBGroupWorkTheme
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.maps.android.compose.GoogleMap
import com.google.maps.android.compose.Marker
import com.google.maps.android.compose.rememberCameraPositionState
import com.google.maps.android.compose.rememberMarkerState
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            DBGroupWorkTheme {
                Scaffold(modifier = Modifier.fillMaxSize(), containerColor = Color(0xFF2C3E50)) { innerPadding ->
                    Greeting(
                        name = "Androaid",
                        modifier = Modifier.padding(innerPadding)
                    )
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MyGoogleMap() {
    val singapore = LatLng(1.3521, 103.8198) // Coordenadas de ejemplo (Singapur)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(singapore, 12f)
    }

    GoogleMap(
        modifier = Modifier.fillMaxSize(),
        cameraPositionState = cameraPositionState
    ) {
        Marker(
            state = rememberMarkerState(position = singapore),
            title = "Marker en Singapur",
            snippet = "¡Aquí estamos!"
        )
    }
}
@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun HomeScreen() {
    Box(modifier = Modifier.fillMaxSize()
        .background(Color(0xFF2C3E50)), contentAlignment = Alignment.Center) {
        Text("Pantalla de Inicio", fontSize = 24.sp)
    }
}

@Composable
fun ProfileScreen() {
    MyGoogleMap()
}

@Composable
fun SettingsScreen() {
    Box(modifier = Modifier.fillMaxSize()
        .background(Color(0xFF2C3E50)), contentAlignment = Alignment.Center) {
        Text("Pantalla de Ajustes", fontSize = 24.sp)
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavBarItem.Home,
        BottomNavBarItem.Profile,
        BottomNavBarItem.Settings
    )

    NavigationBar(containerColor = Color(0xFF34495E)) //0xFF2C3E50
 {
        val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
        items.forEach { item ->
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.title) },
                label = { Text(item.title )},
                selected = currentRoute == item.route,
                onClick = { navController.navigate(item.route) },

                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    selectedTextColor = Color.White,
                    unselectedTextColor = Color.Gray,
                    indicatorColor = Color(0xFF2C3E50)
                )
            )
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = { BottomNavBar(navController) }
    ) { paddingValues ->
        NavHost(
            navController = navController,
            startDestination = BottomNavBarItem.Home.route,
            modifier = Modifier.padding(paddingValues)
                .background(Color(0xFF2C3E50))
        ) {
            composable(BottomNavBarItem.Home.route) { HomeScreen() }
            composable(BottomNavBarItem.Profile.route) { ProfileScreen() }
            composable(BottomNavBarItem.Settings.route) { SettingsScreen() }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    DBGroupWorkTheme {
        Greeting("Android")
    }
}