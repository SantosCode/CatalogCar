package br.com.fiap.catalogcar.presentation.car_list

import br.com.fiap.catalogcar.data.remote.dto.CarDto
import br.com.fiap.catalogcar.domain.model.Car

data class CarListState(
    val isLoading: Boolean = false,
    val cars: List<Car> = emptyList(),
    val car: CarDto? = null,
    val error: String = ""
)
