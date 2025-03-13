package com.example.dbgroupwork.Presentation.Views

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.dbgroupwork.Presentation.DependencyProvider
import com.example.dbgroupwork.Presentation.ViewModels.CommentsState
import com.example.dbgroupwork.Presentation.ViewModels.CommunityViewModel

@Composable
fun CommunityScreen() {
    CommunityTopText()
}

@Composable
fun CommunityTopText(){
    Box(modifier = Modifier.fillMaxSize()){
        Column(modifier = Modifier
            .align(Alignment.TopStart)
            .padding(16.dp)) {

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
        }
    }
    CommentsSection()
}

@Composable
fun CommentsSection(viewModel:CommunityViewModel = viewModel(factory = DependencyProvider.commentsViewModelFactory)) {
    Scaffold(
        topBar = {},
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
                .padding(16.dp)
                .verticalScroll(rememberScrollState()),
        ) {

            val state by viewModel.state.collectAsState()

            when (state) {
                is CommentsState.Error -> {
                    Text(text = (state as CommentsState.Error).message)
                }

                CommentsState.Loading -> {
                    Text(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally)
                            .padding(top = 200.dp),
                        text = "Loading...",
                    )
                }

                is CommentsState.Success -> {
                    var comment by remember { mutableStateOf("") }
                    var author by remember { mutableStateOf("") }

                    TextField(
                        value = comment,
                        onValueChange = { comment = it },
                        label = { Text("Comentario") },
                        modifier = Modifier.padding(bottom = 8.dp)
                    )

                    TextField(
                        value = author,
                        onValueChange = { author = it },
                        label = { Text("Autor") },
                        modifier = Modifier.padding(bottom = 16.dp)
                    )

                    (state as CommentsState.Success).comments.forEach() { comentario ->
                        Text(
                            text = "Autor: ${comentario.author}",
                            modifier = Modifier.padding(bottom = 4.dp)
                        )
                        Text(
                            text = comentario.comment,
                            modifier = Modifier.padding(bottom = 16.dp)
                        )
                    }

                    //Button(
                    //    onClick = {
                    //        viewModel.addComment(Comment(author, comment))
                    //        comment = ""
                    //        author = ""
                    //    },
                    //) {
                    //    Text(text = "Añadir")
                    //}
                }
            }
        }
    }
}
