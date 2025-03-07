package com.example.dbgroupwork.Presentation.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

class CommunityView {
}

@Composable
fun CommunityScreen() {
    Box(modifier = Modifier.fillMaxSize()
        .background(Color(0xFF2C3E50)), contentAlignment = Alignment.Center) {
        Text("Pantalla de comunidad", fontSize = 24.sp)
    }
}