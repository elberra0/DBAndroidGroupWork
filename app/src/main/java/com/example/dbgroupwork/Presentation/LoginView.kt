package com.example.dbgroupwork.Presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class LoginView {
}

@Composable
fun LoginScreen(navController: NavController){
    LogInTopText()

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        var emailOrUsername by remember { mutableStateOf("") }
        var username by remember { mutableStateOf("") }

        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(emailOrUsername, onValueChange = {emailOrUsername = it}, "Username/email", Icons.Filled.AccountBox,true)
        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(username, onValueChange = {username = it}, "Password", Icons.Filled.Lock,true)
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = {navController.navigate("main") },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF2C3E50)
            )

        ) {
            Text(text = "Log in!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(50.dp))
        GoToRegisterScreen(navController)
    }
}
@Composable
fun LogInTopText(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp)) {

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Log in",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            Text(
                text = "Log in with your username or email",
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun GoToRegisterScreen(navController: NavController){
    Row(modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "Don't have an account? ",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Left
        )
        Button(onClick = {navController.navigate("register") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color(0xFF2C3E50)
            )) {
            Text(text = "Sign up",
                fontSize = 15.sp,
                color = Color(0xFF007AFF),
                textAlign = TextAlign.Left,
            )
        }
    }
}
