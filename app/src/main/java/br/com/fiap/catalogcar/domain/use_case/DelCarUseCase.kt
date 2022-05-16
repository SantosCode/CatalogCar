package br.com.fiap.catalogcar.domain.use_case

import br.com.fiap.catalogcar.data.local.datasource.StoreUser
import br.com.fiap.catalogcar.domain.repository.CarRepository
import br.com.fiap.catalogcar.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class DelCarUseCase @Inject constructor(
    private val repository: CarRepository,
    private val storeUser: StoreUser
) {
    operator fun invoke(id: Long): Flow<Resource<Unit>> = flow {
        try {
            emit(Resource.Loading<Unit>())
            val auth = storeUser.getAuth.first()
            if (auth.isNotEmpty()) {
                repository.delCar(auth, id)
                emit(Resource.Success<Unit>(Unit))
            } else {
                emit(Resource.Error<Unit>("Login Failed"))
            }
        } catch (ex: Exception) {
            emit(Resource.Error<Unit>(ex.localizedMessage ?: "Unexpected error occurred"))
        }
    }
}