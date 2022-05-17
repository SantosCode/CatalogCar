package br.com.fiap.catalogcar.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.catalogcar.presentation.car_form.CarFormScreen
import br.com.fiap.catalogcar.presentation.car_list.CarListScreen
import br.com.fiap.catalogcar.presentation.login.LoginScreen

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController, startDestination = "login") {
        composable("login") { LoginScreen(navHostController = navController) }
        composable("carList") { CarListScreen(navHostController = navController) }
        composable("carForm") { CarFormScreen(navHostController = navController) }
    }
}