package com.example.jetpackcomposeegitim


import MyApp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.ViewModelProvider
import androidx.room.Room
import com.example.jetpackcomposeegitim.ui.theme.JetpackComposeEgitimTheme
import dagger.hilt.android.AndroidEntryPoint
import com.example.jetpackcomposeegitim.LoginViewModel





@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
            MyApp()


        }
    }
}