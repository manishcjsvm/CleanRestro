package com.assignment.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.compose.rememberNavController
import com.assignment.cleanrestro.ui.theme.CleanRestroTheme
import com.assignment.presentation.navigation.NavGraph
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