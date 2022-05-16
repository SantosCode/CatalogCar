package br.com.fiap.catalogcar.data.remote.dto

import br.com.fiap.catalogcar.domain.model.Car

data class CarDto(
    val id: Long? = null,
    val model: String,
    val year: Int,
    val  manufacture: String,
    val image: Int? = null
)

fun CarDto.toCar(): Car = Car(
    id = id,
    model = model,
    year = year,
    manufacture = manufacture,
    image = image
)
