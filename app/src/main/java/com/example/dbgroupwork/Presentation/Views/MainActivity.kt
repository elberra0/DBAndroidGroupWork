package com.example.dbgroupwork.Presentation.Views

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import com.example.dbgroupwork.Presentation.Theme.DBGroupWorkTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.TextField
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dbgroupwork.Data.DataStoreManager
import com.example.dbgroupwork.Data.Repository.UserRepositoryImpl
import com.example.dbgroupwork.Domain.UseCaes.CheckUserToLoginUseCase
import com.example.dbgroupwork.Domain.UseCaes.ModifyUserDataUseCase
import com.example.dbgroupwork.Domain.UseCaes.SaveUserDataUseCase
import com.example.dbgroupwork.Domain.UserRepository
import com.example.dbgroupwork.Presentation.ViewModels.LoginViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SettingsScreenViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SignupViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val dataStoreManager = DataStoreManager(applicationContext)
        val userRepository = UserRepositoryImpl(dataStoreManager)
        val saveUserDataUseCase = SaveUserDataUseCase(userRepository)
        val modifyUserDataUseCase = ModifyUserDataUseCase(userRepository)
        val checkUserToLoginUseCase = CheckUserToLoginUseCase(userRepository)
        val viewModel = SignupViewModel(saveUserDataUseCase, userRepository)
        val loginViewModel = LoginViewModel(checkUserToLoginUseCase, userRepository)
        val settingsScreenViewModel = SettingsScreenViewModel(modifyUserDataUseCase, userRepository)
        enableEdgeToEdge()
        setContent {
            DBGroupWorkTheme {
                AppNavHost(viewModel,loginViewModel,modifyUserDataUseCase,userRepository,settingsScreenViewModel)
            }
        }
    }
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
    GoogleMapView()
}

@Composable
fun SettingsTextField(value:String,onValueChange: (String) -> Unit,placeHolderText:String,icon: ImageVector, enabled:Boolean, isPassword:Boolean){
    if(!isPassword) {
        TextField(
            value = value,
            onValueChange = onValueChange,
            placeholder = { Text(placeHolderText, fontSize = 14.sp) },
            enabled = enabled,
            textStyle = TextStyle(fontSize = 14.sp),
            leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(0.70f)
                .height(50.dp),
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )
    }else{
        TextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = PasswordVisualTransformation(),
            placeholder = { Text(placeHolderText, fontSize = 14.sp) },
            enabled = enabled,
            textStyle = TextStyle(fontSize = 14.sp),
            leadingIcon = { Icon(imageVector = icon, contentDescription = null) },
            modifier = Modifier.fillMaxWidth(0.70f)
                .height(50.dp),
            singleLine = true,
            shape = RoundedCornerShape(8.dp)
        )
    }
}

@Composable
fun BottomNavBar(navController: NavController) {
    val items = listOf(
        BottomNavBarItem.Home,
        BottomNavBarItem.Profile,
        BottomNavBarItem.Community,
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
fun MainScreen(modifyUserDataUseCase: ModifyUserDataUseCase, userRepository: UserRepository,settingsScreenViewModel: SettingsScreenViewModel) {
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
            composable(BottomNavBarItem.Community.route) { CommunityScreen() }
            composable(BottomNavBarItem.Settings.route) { SettingsScreen(settingsScreenViewModel) }
        }
    }
}