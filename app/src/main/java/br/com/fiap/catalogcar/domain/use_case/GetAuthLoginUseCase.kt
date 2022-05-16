package br.com.fiap.catalogcar.domain.use_case

import br.com.fiap.catalogcar.data.local.datasource.StoreUser
import br.com.fiap.catalogcar.data.remote.dto.AuthDto
import br.com.fiap.catalogcar.domain.repository.CarRepository
import br.com.fiap.catalogcar.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Credentials
import javax.inject.Inject

class GetAuthLoginUseCase @Inject constructor(
    private val repository: CarRepository,
    private val storeUser: StoreUser,
) {
    operator fun invoke(user: String, password: String): Flow<Resource<AuthDto>> = flow {

        try {
            emit(Resource.Loading<AuthDto>())
            val credentials: String = Credentials.basic(user, password)
            val response = repository.login(credentials)
            if (response.isSuccessful) {
                response.body()?.let {
                    storeUser.saveUserPassword(it.token)
                    emit(Resource.Success<AuthDto>(it))
                }
            } else {
                emit(Resource.Error<AuthDto>("Invalid Login"))
            }
        } catch (ex: Exception) {
            emit(Resource.Error<AuthDto>(ex.localizedMessage
                ?: "Unexpected error occurred"))
        }
    }
}