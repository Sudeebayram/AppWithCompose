import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.jetpackcomposeegitim.AddTaskScreen
import com.example.jetpackcomposeegitim.LoginScreen
import com.example.jetpackcomposeegitim.RegisterScreen
import com.example.jetpackcomposeegitim.TaskDetailScreen
import com.example.jetpackcomposeegitim.TaskRepository
import com.example.jetpackcomposeegitim.TaskViewModel
import com.example.jetpackcomposeegitim.TaskViewModelFactory
import com.example.jetpackcomposeegitim.TasksScreen
import com.example.jetpackcomposeegitim.Task
import com.example.jetpackcomposeegitim.database.TaskDatabase

@Composable
fun MyApp() {
    val navController = rememberNavController()


    val context = LocalContext.current
    val taskDao = remember { TaskDatabase.getDatabase(context).taskDao() }


    val repository = remember { TaskRepository(taskDao) }


    val viewModel: TaskViewModel = remember {
        TaskViewModelFactory(repository).create(TaskViewModel::class.java)
    }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController)
        }
        composable("register") {
            RegisterScreen(navController)
        }
        composable("tasks") {
            TasksScreen(
                viewModel = viewModel,
                onAddTaskClick = { navController.navigate("addTask") },
                onTaskClick = { taskId -> navController.navigate("taskDetail/$taskId") }
            )
        }
        composable("addTask") {
            AddTaskScreen(
                onTaskAdd = { task -> viewModel.insertTask(task) },
                onBack = { navController.popBackStack() }
            )
        }
        composable("taskDetail/{taskId}") { backStackEntry ->
            val taskId = backStackEntry.arguments?.getString("taskId")?.toIntOrNull()
            var task by remember { mutableStateOf<Task?>(null) }


            LaunchedEffect(taskId) {
                if (taskId != null) {
                    task = viewModel.getTaskById(taskId)
                }
            }


            task?.let {
                TaskDetailScreen(
                    task = it,
                    onTaskUpdate = { updatedTask -> viewModel.insertTask(updatedTask) },
                    onTaskDelete = { viewModel.deleteTask(it) },
                    onBack = { navController.popBackStack() }
                )
            }
        }
    }
}
