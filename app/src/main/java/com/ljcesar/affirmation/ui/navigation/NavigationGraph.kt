package com.ljcesar.affirmation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ljcesar.affirmation.CountryScreen
import com.ljcesar.affirmation.HomeScreen

@Composable
fun NavigationGraph(){
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home"){
        composable("home"){
            HomeScreen(navController = navController)
        }
        composable("countries"){
            CountryScreen(navController = navController)
        }
    }
}