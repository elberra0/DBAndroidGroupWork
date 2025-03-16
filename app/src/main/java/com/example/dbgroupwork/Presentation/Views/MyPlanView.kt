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
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.dbgroupwork.Presentation.DependencyProvider
import com.example.dbgroupwork.Presentation.ViewModels.MyPlanViewModel
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyPlanView(navController: NavController, viewModel: MyPlanViewModel = viewModel(factory = DependencyProvider.myPlanViewModel)){
    Spacer(modifier = Modifier.height(20.dp))
    Column(
        modifier = Modifier.fillMaxSize()
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //val context = LocalContext.current
        val diaSemana = remember {
            LocalDate.now().dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US)
        }

       val day =  when (diaSemana) {
            "Sunday" -> "domingo"
            "Monday" -> "lunes"
            "Tuesday" -> "martes"
            "Wednesday" -> "miercoles"
            "Thursday" -> "jueves"
            "Friday" -> "viernes"
            "Saturday" -> "sabado"
           else -> "lunes"
        }

        runBlocking {
            viewModel.getMyPlan()
        }

        MyPlanTopText()

        Text(
            text = viewModel.myPlan!!.clasificacion,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = "Esta el tu rutina para el  $day:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Medium,
            color = Color.White,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Text(
            text = viewModel.myPlan!!.ejercicios["miercoles"]!!.tipo,
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = "Ejercicios de tipo ${viewModel.myPlan!!.ejercicios["martes"]!!.tipo}:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Text(
            text = if(viewModel.myPlan!!.ejercicios["martes"]!!.calentamiento.isNullOrEmpty()) "" else "Calentamiento ${viewModel.myPlan!!.ejercicios["martes"]!!.calentamiento}:",
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            color = Color.White,
            textAlign = TextAlign.Left,
            modifier = Modifier.padding(bottom = 16.dp)
        )


        Column {
            viewModel.myPlan!!.ejercicios["martes"]!!.ejercicios.forEach { ejercicio ->
                OutlinedTextField(
                    value =  if(ejercicio.repeticiones.isNullOrEmpty()) "" else "De ${ejercicio.repeticiones} repeticiones en ${ejercicio.series} series. "  ,
                    onValueChange = {  },
                    label = { Text(ejercicio.nombre,color = Color.White, fontSize = 20.sp) },
                    textStyle = androidx.compose.ui.text.TextStyle(fontSize = 20.sp, color = Color.White),
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))

        Button(
            onClick = {
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
            Text(text = "Done!", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        Spacer(modifier = Modifier.height(50.dp))
    }
}

@Composable
fun  MyPlanTopText(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp)) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "My Plan",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            Text(
                text = "Today is a good day for fitness.",
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 16.dp)
            )
        }
    }
}

@Composable
fun ffGoToHomeScreen(navController: NavController){
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