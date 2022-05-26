package br.com.fiap.catalogcar.presentation.car_form

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.catalogcar.data.remote.dto.CarDto
import br.com.fiap.catalogcar.domain.use_case.AddCarUseCase
import br.com.fiap.catalogcar.domain.use_case.GetCarUseCase
import br.com.fiap.catalogcar.presentation.car_list.CarListState
import br.com.fiap.catalogcar.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CarFormViewMode @Inject constructor(
    private val addCarUseCase: AddCarUseCase,
    private val getCarUseCase: GetCarUseCase,
) : ViewModel() {

    private val _state = mutableStateOf(CarListState())
    val state: State<CarListState> = _state

    fun addCar(
        id: Long? = null,
        model: String,
        manufacturer: String,
        year: Int,
    ) {
        val car = CarDto(
            id = id,
            model = model,
            manufacture = manufacturer,
            year = year
        )
        addCarUseCase(car = car).onEach { result ->
            when (result) {
                is Resource.Success -> _state.value =
                    CarListState(car = result.data)
                is Resource.Error -> _state.value =
                    CarListState(error = result.message ?: "An unexpected error occurred")
                is Resource.Loading -> _state.value =
                    CarListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

    fun editCar(id: Long)  {
            getCarUseCase(id).onEach { result ->
                when (result) {
                    is Resource.Success -> _state.value =
                        CarListState(car = result.data)
                    is Resource.Error -> _state.value =
                        CarListState(error = result.message ?: "An unexpected error occurred")
                    is Resource.Loading -> _state.value =
                        CarListState(isLoading = true)
                }
            }.launchIn(viewModelScope)
        }
}