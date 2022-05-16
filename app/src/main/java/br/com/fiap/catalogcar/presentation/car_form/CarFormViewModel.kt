package br.com.fiap.catalogcar.presentation.car_form

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import br.com.fiap.catalogcar.data.remote.dto.CarDto
import br.com.fiap.catalogcar.domain.use_case.AddCarUseCase
import br.com.fiap.catalogcar.presentation.car_list.CarListState
import br.com.fiap.catalogcar.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarFormViewMode @Inject constructor(
    private val addCarUseCase: AddCarUseCase
): ViewModel() {

    private val _state = mutableStateOf(CarListState())
    val state: State<CarListState> = _state

    fun addCar(
        model: String,
        manufacturer: String,
        year: Int,
        navController: NavHostController
    ) {
        val car = CarDto(
            model = model,
            manufacture = manufacturer,
            year = year
        )
        addCarUseCase(car = car).onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value =
                        CarListState(car = result.data)
                    navController.navigate("carList")
                }
                is Resource.Error -> _state.value =
                    CarListState(error = result.message ?:  "An unexpected error occurred")
                is Resource.Loading -> _state.value =
                    CarListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}