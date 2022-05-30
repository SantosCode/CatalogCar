package br.com.fiap.catalogcar.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.ExperimentalComposeApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.fiap.catalogcar.presentation.car_form.CarFormScreen
import br.com.fiap.catalogcar.presentation.car_list.CarListScreen
import br.com.fiap.catalogcar.presentation.login.LoginScreen
import br.com.fiap.catalogcar.presentation.login.components.Authentication
import br.com.fiap.catalogcar.route.NavRoute

@ExperimentalComposeApi
@Composable
fun NavigationComponent(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoute.Auth.path) {
        addAuthValidator(navController, this)
        addLoginScreen(navController, this)
        addCarListScreen(navController, this)
        addCarFormScreen(navController, this)
        addEditCarScreen(navController, this)
    }
}

private fun addAuthValidator(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(route = NavRoute.Auth.path) {
        Authentication(
            navigateToLogin = {
                navController.navigate(route = NavRoute.Login.path)
            },
            navigateToCarList = {
                navController.navigate(route = NavRoute.CarList.path)
            })
    }
}

private fun addLoginScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(NavRoute.Login.path) {
        LoginScreen(navigateToCarList = {
            navController.navigate(NavRoute.CarList.path)
        })
    }
}

private fun addCarFormScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(NavRoute.CarForm.path) {
        CarFormScreen(navigateToCarList = {
            navController.navigate(NavRoute.CarList.path)
        })
    }
}

private fun addEditCarScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(
        route = NavRoute.CarForm.withArgsFormat(NavRoute.CarFormEdit.id),
        arguments = listOf(
            navArgument(NavRoute.CarFormEdit.id) {
                type = NavType.LongType
            }
        )
    ) { navBackStackEntry ->
        val args = navBackStackEntry.arguments
        CarFormScreen(
            id = args?.getLong(NavRoute.CarFormEdit.id),
            navigateToCarList = { navController.navigate(NavRoute.CarList.path) }
        )
    }
}

private fun addCarListScreen(
    navController: NavHostController,
    navGraphBuilder: NavGraphBuilder,
) {
    navGraphBuilder.composable(NavRoute.CarList.path) {
        CarListScreen(
            navigateToCarForm = {
                navController.navigate(NavRoute.CarForm.path)
            },
            navigateToLogin = {
                navController.navigate(NavRoute.Login.path)
            },
            navigateToCarEdit = { id ->
                navController.navigate(NavRoute.CarForm.withArgs(id.toString()))
            },
            navigateToCarList = {
                navController.navigate(NavRoute.CarList.path)
            }
        )
    }
}
