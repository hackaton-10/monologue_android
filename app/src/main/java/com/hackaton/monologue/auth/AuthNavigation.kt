package com.hackaton.monologue.auth

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.hackaton.monologue.auth.login.LoginScreen
import com.hackaton.monologue.auth.signup.SignupScreen
import com.hackaton.monologue.MainNavigation
import com.hackaton.monologue.MainScreen

@Composable
fun AuthNavigation() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "login"
    ) {
        composable("login") {
            LoginScreen(
                onButtonClick = {
                    navController.navigate("main")
                },
                onTextClick = {
                    navController.navigate("signup")
                }
            )
        }
        composable("signup") {
            SignupScreen(
                onButtonClick = {
                    navController.navigate("login")
                }
            )
        }
        composable("main") {
            MainScreen()
        }
    }
}