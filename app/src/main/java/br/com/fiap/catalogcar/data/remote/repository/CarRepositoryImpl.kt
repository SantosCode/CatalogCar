package br.com.fiap.catalogcar.data.remote.repository

import br.com.fiap.catalogcar.data.remote.CarApi
import br.com.fiap.catalogcar.data.remote.dto.AuthDto
import br.com.fiap.catalogcar.data.remote.dto.CarDto
import br.com.fiap.catalogcar.domain.repository.CarRepository
import retrofit2.Response
import javax.inject.Inject

class CarRepositoryImpl @Inject constructor(
    private val api: CarApi
) : CarRepository {

    override suspend fun login(auth: String): Response<AuthDto> {
        return api.login(auth)
    }

    override suspend fun getCars(auth: String): List<CarDto> {
        return api.getCars(auth)
    }

    override suspend fun addCar(auth: String, car: CarDto) {
        return api.addCar(auth = auth, car = car)
    }

    override suspend fun delCar(auth: String, id: Long) {
        return api.delCar(auth, id)
    }
}