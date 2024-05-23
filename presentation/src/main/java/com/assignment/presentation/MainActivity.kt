package com.assignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.assignment.presentation.navigation.NavGraph
import com.assignment.presentation.ui.theme.CleanRestroTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            rememberNavController().run {
                CleanRestroTheme {
                    NavGraph(navHostController = this)
                }
            }
        }
    }
}