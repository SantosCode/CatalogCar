package br.com.fiap.catalogcar.domain.use_case

import br.com.fiap.catalogcar.data.local.datasource.StoreUser
import br.com.fiap.catalogcar.data.remote.dto.toCar
import br.com.fiap.catalogcar.domain.model.Car
import br.com.fiap.catalogcar.domain.repository.CarRepository
import br.com.fiap.catalogcar.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllCarsUseCase @Inject constructor(
    private val repository: CarRepository,
    private val storeUser: StoreUser
) {
    operator fun invoke(): Flow<Resource<List<Car>>> = flow {

        try {
            emit(Resource.Loading<List<Car>>())
            val auth = storeUser.getAuth.first()
            if (auth.isNotEmpty()) {
                val response = repository.getCars(auth).map { it.toCar() }
                emit(Resource.Success<List<Car>>(response))
            } else {
                emit(Resource.Error<List<Car>>("Login Failed"))
            }
        } catch (ex: Exception) {
            emit(Resource.Error<List<Car>>(ex.localizedMessage ?: "Unexpected error occurred"))
        } catch (ex: Exception) {
            emit(Resource.Error<List<Car>>("Couldn't reach server. Check your internet connection"))
        }
    }
}