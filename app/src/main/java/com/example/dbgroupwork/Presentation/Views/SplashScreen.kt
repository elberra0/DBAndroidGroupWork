package com.example.dbgroupwork.Presentation.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.dbgroupwork.Domain.UseCaes.ModifyUserDataUseCase
import com.example.dbgroupwork.Domain.UserRepository
import com.example.dbgroupwork.Presentation.ViewModels.LoginViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SettingsScreenViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SignupViewModel
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navigateToMainScreen: () -> Unit){
    LaunchedEffect(Unit) {
        delay(2000)
        navigateToMainScreen()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2C3E50)),
        contentAlignment = Alignment.Center
    ) {
        Icon(
            imageVector = Icons.Filled.Favorite,
            contentDescription = "App logo",
            modifier = Modifier.size(100.dp),
            tint = Color.White
        )
    }
}

@Composable
fun AppNavHost(signupViewModel: SignupViewModel, loginViewModel: LoginViewModel,modifyUserDataUseCase: ModifyUserDataUseCase,userRepository: UserRepository,settingsScreenViewModel: SettingsScreenViewModel) {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "splash", modifier = Modifier.background(Color(0xFF2C3E50))) {
        composable("splash") {
            SplashScreen(
                navigateToMainScreen = {
                    navController.navigate("login") {
                        popUpTo("splash") { inclusive = true }
                    }
                }
            )
        }
        composable("main") {
            MainScreen(modifyUserDataUseCase,userRepository,settingsScreenViewModel)
        }
        composable("login") {
            LoginScreen(navController,loginViewModel)
        }
        composable("register") {
            SignUpScreen(navController, signupViewModel)
        }
    }
}