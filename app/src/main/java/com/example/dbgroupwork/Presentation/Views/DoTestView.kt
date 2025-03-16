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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dbgroupwork.Presentation.DependencyProvider
import com.example.dbgroupwork.Presentation.SettingsTextField
import com.example.dbgroupwork.Presentation.ViewModels.DoTestViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SignupViewModel

@Composable
fun DoTestView(viewModel: DoTestViewModel = viewModel(factory = DependencyProvider.doTestViewModel)){
    DoTestTopText()
/*
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current

        Spacer(modifier = Modifier.height(20.dp))
        SettingsTextField(email, onValueChange = { viewModel.onEmailChanged(it) }, "Email", Icons.Filled.Email,true,false)
        Spacer(modifier = Modifier.height(20.dp))

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
            Text(text = "do test", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(50.dp))
        GoToHomeScreen(navController)
    }
    */
}
@Composable
fun DoTestTopText(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp)) {

            Spacer(modifier = Modifier.height(100.dp))

            Text(
                text = "Do test",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            Text(
                text = "Determine the exercise plan",
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun GoToHomeScreen(navController: NavController){
    Row(modifier = Modifier,
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = "Already have an account? ",
            fontSize = 15.sp,
            color = Color.Gray,
            textAlign = TextAlign.Left
        )
        Button(onClick = { navController.navigate("main") },
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