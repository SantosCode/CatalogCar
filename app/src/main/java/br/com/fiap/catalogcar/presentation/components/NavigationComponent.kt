package br.com.fiap.catalogcar.presentation.components

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.fiap.catalogcar.presentation.car_form.CarFormScreen
import br.com.fiap.catalogcar.presentation.car_list.CarListScreen
import br.com.fiap.catalogcar.presentation.login.LoginScreen
import br.com.fiap.catalogcar.utils.Constants

@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController, startDestination = Constants.LOGIN_VIEW) {
        composable(Constants.LOGIN_VIEW) { LoginScreen(navHostController = navController) }
        composable(Constants.CAR_LIST_VIEW) { CarListScreen(navHostController = navController) }
        composable(Constants.CAR_FORM_VIEW) { CarFormScreen(navHostController = navController) }
    }
}