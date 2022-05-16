package br.com.fiap.catalogcar.data.remote.dto

import com.google.gson.annotations.SerializedName

data class AuthDto(
    @SerializedName("Authorization") val token: String
)
