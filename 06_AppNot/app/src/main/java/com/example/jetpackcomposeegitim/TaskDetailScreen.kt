package com.example.jetpackcomposeegitim

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack

@Composable
fun TaskDetailScreen(
    task: Task?,
    onTaskUpdate: (Task) -> Unit,
    onTaskDelete: (Task) -> Unit,
    onBack: () -> Unit
) {

    var title by remember { mutableStateOf(task?.title ?: "") }
    var description by remember { mutableStateOf(task?.description ?: "") }
    var deadline by remember { mutableStateOf(task?.deadline ?: "") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {


        IconButton(onClick = { onBack() }) {
            Icon(
                imageVector = Icons.Filled.ArrowBack,
                contentDescription = "Geri",
                tint = Color.Black // İkona renk verebilirsiniz
            )
        }
        Spacer(modifier = Modifier.height(16.dp))

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Görev Adı") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Açıklama") }
        )
        Spacer(modifier = Modifier.height(8.dp))

        TextField(
            value = deadline,
            onValueChange = { deadline = it },
            label = { Text("Son Tarih") }
        )
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Button(
                onClick = {
                    task?.let {
                        onTaskDelete(it)
                        onBack()
                    }
                },
                enabled = task != null
            ) {
                Text("Sil")
            }

            Button(
                onClick = {
                    task?.let {
                        onTaskUpdate(Task(it.id, title, description, deadline))
                        onBack()
                    }
                },
                enabled = task != null
            ) {
                Text("Güncelle")
            }
        }


    }
}
