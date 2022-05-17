package br.com.fiap.catalogcar.presentation.car_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import br.com.fiap.catalogcar.domain.use_case.DelCarUseCase
import br.com.fiap.catalogcar.domain.use_case.GetCarUseCase
import br.com.fiap.catalogcar.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val getCarUseCase: GetCarUseCase,
    private val delCarUseCase: DelCarUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(CarListState())
    val state: State<CarListState> = _state

    init {
        getCar()
    }

    private fun getCar() {
        getCarUseCase().onEach { result ->
            when (result) {
                is Resource.Success -> _state.value =
                    CarListState(cars = result.data ?: emptyList())
                is Resource.Error -> _state.value =
                    CarListState(error = result.message ?: "An unexpected error occurred")
                is Resource.Loading -> _state.value =
                    CarListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }

    fun deleteCar(
        id: Long,
        navHostController: NavHostController,
    ) {
        delCarUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> navHostController.navigate("carList")
                is Resource.Error -> _state.value =
                    CarListState(error = result.message ?: "An unexpected error occurred")
                is Resource.Loading -> _state.value =
                    CarListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}