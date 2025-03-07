package com.example.dbgroupwork.Presentation.Views

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
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.dbgroupwork.Presentation.ViewModels.SignupViewModel

@Composable
fun SignUpScreen(navController: NavController, viewModel:SignupViewModel){
    SignUpTopText()

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        val email by viewModel.email.collectAsState()
        val username by viewModel.username.collectAsState()
        val password by viewModel.password.collectAsState()
        val confirmPassword by viewModel.passwordConfirm.collectAsState()

        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(email, onValueChange = { viewModel.onEmailChanged(it) }, "Email", Icons.Filled.Email,true,false)
        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(username, onValueChange = { viewModel.onUsernameChanged(it) }, "Username", Icons.Filled.AccountCircle,true,false)
        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(password, onValueChange = { viewModel.onPasswordChanged(it) }, "Password",Icons.Filled.Lock,true,true)
        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(confirmPassword, onValueChange = {viewModel.onPasswordConfirmChanged(it)}, "Confirm Password",Icons.Filled.CheckCircle,password.isNotEmpty(),true)
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = { viewModel.saveUserData(context)},
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF2C3E50)
            )

        ) {
            Text(text = "Sign up!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(50.dp))
        GotoLoginScreen(navController)
    }
}
@Composable
fun SignUpTopText(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp)) {

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Sign up",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            Text(
                text = "Sign up to access FIT APP services",
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun GotoLoginScreen(navController: NavController){
    Row(modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "Already have an account? ",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Left
        )
        Button(onClick = { navController.navigate("login") },
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Transparent,
                contentColor = Color(0xFF2C3E50)
            )) {
            Text(text = "Log in",
                fontSize = 15.sp,
                color = Color(0xFF007AFF),
                textAlign = TextAlign.Left,
            )
        }
    }
}