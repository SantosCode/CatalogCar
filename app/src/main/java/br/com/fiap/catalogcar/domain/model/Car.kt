package br.com.fiap.catalogcar.domain.model

data class Car(
    val id: Long? = null,
    val model: String,
    val year: Int,
    val  manufacture: String,
    val image: Int? = null
)
