package br.com.fiap.catalogcar.presentation.login

import br.com.fiap.catalogcar.data.remote.dto.AuthDto

data class LoginState(
    val isLoading: Boolean = false,
    val auth: AuthDto? = null,
    val error: String = ""
)