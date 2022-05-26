package br.com.fiap.catalogcar.presentation.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.catalogcar.data.local.datasource.StoreUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class AuthenticationViewModel @Inject constructor(
    private val storeUser: StoreUser,
) : ViewModel() {

    fun verifyLoggedIn(
        navigateLogin: () -> Unit,
        navigateCarList: () -> Unit
    ) {
        storeUser.getAuth.onEach { result ->
            if (result.isNotEmpty()) {
                navigateCarList().also {
                    viewModelScope.cancel()
                }
            } else {
                navigateLogin().also {
                    viewModelScope.cancel()
                }
            }
        }.launchIn(viewModelScope)
    }
}