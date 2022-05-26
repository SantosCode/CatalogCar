package br.com.fiap.catalogcar.domain.use_case

import br.com.fiap.catalogcar.data.local.datasource.StoreUser
import br.com.fiap.catalogcar.data.remote.dto.CarDto
import br.com.fiap.catalogcar.data.remote.dto.toCar
import br.com.fiap.catalogcar.domain.model.Car
import br.com.fiap.catalogcar.domain.repository.CarRepository
import br.com.fiap.catalogcar.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCarUseCase @Inject constructor(
    private val repository: CarRepository,
    private val storeUser: StoreUser,
) {
    operator fun invoke(id: Long): Flow<Resource<CarDto>> = flow {

        try {
            emit(Resource.Loading<CarDto>())
            val auth = storeUser.getAuth.first()
            if (auth.isNotEmpty()) {
                val response = repository.getCar(auth, id)
                emit(Resource.Success<CarDto>(response))
            } else {
                emit(Resource.Error<CarDto>("Login Failed"))
            }
        } catch (ex: Exception) {
            emit(Resource.Error<CarDto>(ex.localizedMessage ?: "Unexpected error occurred"))
        } catch (ex: Exception) {
            emit(Resource.Error<CarDto>("Couldn't reach server. Check your internet connection"))
        }
    }
}