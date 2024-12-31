package com.example.jetpackcomposeegitim

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TasksScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit,
    onAddTaskClick: () -> Unit
) {

    val tasks by viewModel.allTasks.collectAsState(initial = emptyList())


    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp)) {

        Text("Görevler", style = MaterialTheme.typography.headlineSmall)

        Spacer(modifier = Modifier.height(16.dp))


        LazyColumn {
            items(tasks) { task ->
                TaskItem(task = task, onTaskClick = { onTaskClick(task.id) })
            }
        }

        Spacer(modifier = Modifier.weight(1f))


        Button(onClick = onAddTaskClick, modifier = Modifier.align(Alignment.CenterHorizontally)) {
            Text("Görev Ekle")
        }
    }
}

@Composable
fun TaskItem(task: Task, onTaskClick: () -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
            .clickable(onClick = onTaskClick),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {

            Text(task.title, style = MaterialTheme.typography.bodyLarge)


            Text(task.deadline, style = MaterialTheme.typography.bodySmall)
        }
    }
}
