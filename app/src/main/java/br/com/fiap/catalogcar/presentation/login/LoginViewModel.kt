package br.com.fiap.catalogcar.presentation.login

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import br.com.fiap.catalogcar.data.local.datasource.StoreUser
import br.com.fiap.catalogcar.domain.use_case.GetAuthLoginUseCase
import br.com.fiap.catalogcar.utils.Constants
import br.com.fiap.catalogcar.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: GetAuthLoginUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(LoginState())
    val state: State<LoginState> = _state

    fun login(user: String, password: String, navController: NavHostController) {
        loginUseCase(user, password).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        LoginState(auth = result.data)
                    navController.navigate(Constants.CAR_LIST_VIEW)
                }
                is Resource.Error -> _state.value =
                    LoginState(error = result.message ?: "An unexpected error occurred")
                is Resource.Loading -> _state.value =
                    LoginState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}
