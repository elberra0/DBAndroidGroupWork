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
import androidx.compose.material.icons.filled.AccountBox
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
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dbgroupwork.Presentation.DependencyProvider
import com.example.dbgroupwork.Presentation.Feature.CustomButton
import com.example.dbgroupwork.Presentation.SettingsTextField
import com.example.dbgroupwork.Presentation.ViewModels.HomeViewModel
import com.example.dbgroupwork.Presentation.ViewModels.LoginViewModel

@Composable
fun HomeScreen(navController: NavController, viewModel: HomeViewModel = viewModel(factory = DependencyProvider.homeViewModel)) {

    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        CustomButton(text = "Do test") {
            navController.navigate("dotest")
        }

        Spacer(Modifier.height(150.dp))

        CustomButton(text = "My plan") {navController.navigate("myplan") }
    }
}

