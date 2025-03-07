package com.example.dbgroupwork.Presentation.Views

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import com.example.dbgroupwork.Presentation.ViewModels.SettingsScreenViewModel

class SettingsScreenView {
}

@Composable
fun SettingsScreen(viewModel: SettingsScreenViewModel) {
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

        Text(
            text = "Settings",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 5.dp)
        )

        Text(
            text = "Change your user information",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(email, onValueChange = {viewModel.onEmailChanged(it)}, "New Email", Icons.Filled.Email,true,false)
        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(username, onValueChange = {viewModel.onUsernameChanged(it)}, "New Username", Icons.Filled.AccountCircle,true,false)
        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(password, onValueChange = {viewModel.onPasswordChanged(it)}, "New Password",
            Icons.Filled.Lock,true,true)
        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(confirmPassword, onValueChange = {viewModel.onPasswordConfirmChanged(it)}, "Confirm New Password",
            Icons.Filled.CheckCircle,
            password.isNotEmpty(),true
        )
        Spacer(modifier = Modifier.height(50.dp))

        Button(
            onClick = { viewModel.modifyUserData(context) },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF2C3E50)
            )

        ) {
            Text(text = "Validate", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }

    }
}