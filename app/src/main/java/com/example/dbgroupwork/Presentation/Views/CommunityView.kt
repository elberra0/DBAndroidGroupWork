package com.example.dbgroupwork.Presentation.Views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dbgroupwork.Domain.Models.Comment
import com.example.dbgroupwork.Presentation.DependencyProvider
import com.example.dbgroupwork.Presentation.SettingsTextField
import com.example.dbgroupwork.Presentation.ViewModels.CommentsState
import com.example.dbgroupwork.Presentation.ViewModels.CommunityViewModel

@Composable
fun CommunityScreen() {
    CommunityTopText()
}

@Composable
fun CommunityTopText() {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .align(Alignment.TopStart)
                .padding(16.dp)
        ) {

            Spacer(modifier = Modifier.height(50.dp))

            Text(
                text = "Community",
                fontSize = 30.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center,
                modifier = Modifier.padding(bottom = 5.dp)
            )

            Text(
                text = "Share your trainings or thoughts with the rest of the users",
                fontSize = 15.sp,
                color = Color.Gray,
                textAlign = TextAlign.Left,
                modifier = Modifier.padding(bottom = 16.dp)
            )
            CommentsSection()
        }
    }
}

@Composable
fun CommentsSection(viewModel: CommunityViewModel = viewModel(factory = DependencyProvider.commentsViewModelFactory)) {
    val state by viewModel.state.collectAsState()

    when (state) {
        is CommentsState.Error -> {
            Text(text = (state as CommentsState.Error).message)
        }

        CommentsState.Loading -> {
            Text(
                modifier = Modifier
                    .padding(top = 200.dp),
                text = "Loading...",
            )
        }

        is CommentsState.Success -> {
            var comment by remember { mutableStateOf("") }
            var author by remember { mutableStateOf("") }
            val context = LocalContext.current

            Column(
                modifier = Modifier
                    .padding(horizontal = 8.dp)
                    .fillMaxWidth()
                ,
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                SettingsTextField(
                    value = author,
                    onValueChange = { author = it },
                    "Author",
                    Icons.Filled.Person,
                    true,
                    false
                )

                SettingsTextField(
                    value = comment,
                    onValueChange = { comment = it },
                    "Comment",
                    Icons.Filled.Add,
                    true,
                    false
                )

                Button(
                    onClick = {
                        viewModel.addComment(comment, author,context)
                        comment = ""
                        author = ""
                    }, modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .padding(10.dp)
                        .height(50.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF2C3E50)
                    )
                ) {
                    Text(text = "Send Comment ")

                    Icon(
                        imageVector = Icons.Filled.Send,
                        contentDescription = "Send Comment",
                        modifier = Modifier.size(20.dp)
                    )
                }
            }

            Scaffold(
                topBar = {},    contentWindowInsets = WindowInsets(0.dp, 0.dp, 0.dp, 0.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(it)
                        .verticalScroll(rememberScrollState())
                        .background(Color(0xFF34495E))
                        .clip(RoundedCornerShape(60.dp))
                ) {
                    Spacer(modifier = Modifier.height(20.dp))

                    (state as CommentsState.Success).comments.forEach() { commentToSave ->
                        Text(
                            text = "Autor: ${commentToSave.author}",
                            modifier = Modifier.padding(bottom = 4.dp)
                                .padding(horizontal = 20.dp)
                                .background(Color(0xFF34495E)),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                        Text(
                            text = commentToSave.comment,
                            modifier = Modifier.padding(bottom = 16.dp)
                                .padding(horizontal = 20.dp)
                                .background(Color(0xFF34495E)),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.Light,
                            color = Color.White,
                            textAlign = TextAlign.Center,
                        )
                    }
                }
            }
        }
    }
}
