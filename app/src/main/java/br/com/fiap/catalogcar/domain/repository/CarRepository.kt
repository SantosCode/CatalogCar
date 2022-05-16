package br.com.fiap.catalogcar.domain.repository

import br.com.fiap.catalogcar.data.remote.dto.AuthDto
import br.com.fiap.catalogcar.data.remote.dto.CarDto
import retrofit2.Response

interface CarRepository {

    suspend fun login(auth: String): Response<AuthDto>

    suspend fun getCars(auth: String): List<CarDto>

    suspend fun addCar(auth: String, car: CarDto)

    suspend fun delCar(auth: String, id: Long)
}