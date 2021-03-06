package br.com.fiap.catalogcar.presentation.login.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import br.com.fiap.catalogcar.presentation.login.AuthenticationViewModel

@Composable
fun Authentication(
    viewModel: AuthenticationViewModel = hiltViewModel(),
    navigateToLogin: () -> Unit,
    navigateToCarList: () -> Unit,
) {
    LaunchedEffect(Unit) {
        viewModel.verifyLoggedIn(navigateToLogin, navigateToCarList)
    }
}