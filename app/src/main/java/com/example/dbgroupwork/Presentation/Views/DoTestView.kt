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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dbgroupwork.Presentation.DependencyProvider
import com.example.dbgroupwork.Presentation.SettingsTextField
import com.example.dbgroupwork.Presentation.ViewModels.DoTestViewModel
import com.example.dbgroupwork.Presentation.ViewModels.SignupViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoTestView(navController: NavController, viewModel: DoTestViewModel = viewModel(factory = DependencyProvider.doTestViewModel)){
    DoTestTopText()
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context = LocalContext.current
        val age by viewModel.age.collectAsState()

        val genderOptions = listOf("Male", "Female")
        val genderSelected by viewModel.genderSelected.collectAsState()
        var expanded = remember { mutableStateOf(false) }

        val weight by viewModel.weight.collectAsState()
        val height by viewModel.height.collectAsState()

        val goalOptions = listOf("Gain muscle mass", "Lose weight", "Maintain weight")
        val goalSelected by viewModel.goalSelected.collectAsState()
        var goalexpanded = remember { mutableStateOf(false) }


        OutlinedTextField(
            value = if (age == 0) "" else age.toString(),
            onValueChange = { viewModel.onAgeChange(it) },
            label = { Text("Age",color = Color.White) },
            placeholder = { Text("Enter your age",color = Color.White) },
            textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = expanded.value,
            onExpandedChange = { expanded.value = it }
        ) {
            OutlinedTextField(
                value = genderSelected,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select an option",color = Color.White) },
                textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            ExposedDropdownMenu(
                expanded = expanded.value,
                onDismissRequest = { expanded.value = false }
            ) {
                genderOptions.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            viewModel.onGenderChange(opcion)
                            expanded.value = false
                        }
                    )
                }
            }
        }

        OutlinedTextField(
            value = if (weight == 0) "" else weight.toString(),
            onValueChange = { viewModel.onweightChange(it) },
            label = { Text("Current weight (kg)",color = Color.White) },
            placeholder = { Text("Enter your current weight",color = Color.White) },
            textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        OutlinedTextField(
            value = if (height == 0) "" else height.toString(),
            onValueChange = { viewModel.onheightChange(it) },
            label = { Text("Current height (cm)",color = Color.White) },
            placeholder = { Text("Enter your current height",color = Color.White) },
            textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
        )

        ExposedDropdownMenuBox(
            expanded = goalexpanded.value,
            onExpandedChange = { goalexpanded.value = it }
        ) {
            OutlinedTextField(
                value = goalSelected,
                onValueChange = {},
                readOnly = true,
                label = { Text("Select an option",color = Color.White) },
                textStyle = TextStyle(fontSize = 14.sp, color = Color.White),
                modifier = Modifier
                    .menuAnchor()
                    .fillMaxWidth()
                    .padding(8.dp)
            )
            ExposedDropdownMenu(
                expanded = goalexpanded.value,
                onDismissRequest = { goalexpanded.value = false }
            ) {
                goalOptions.forEach { opcion ->
                    DropdownMenuItem(
                        text = { Text(opcion) },
                        onClick = {
                            viewModel.onGoalChange(opcion)
                            goalexpanded.value = false
                        }
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = {
                viewModel.assignPlan()
                navController.navigate("main")
            },
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(50.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color(0xFF2C3E50)
            )

        ) {
            Text(text = "Let's go", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun DoTestTopText(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp)) {

            Spacer(modifier = Modifier.height(50.dp))

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
            Text(text = "Complete",
                fontSize = 15.sp,
                color = Color(0xFF007AFF),
                textAlign = TextAlign.Left,
            )
        }
    }
}

/*
@Preview(showBackground = false, showSystemUi = false)
@Composable
fun PreviewDoTestView() {
   // DoTestView()
}
 */