package br.com.fiap.catalogcar.presentation.car_list

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.fiap.catalogcar.domain.use_case.DelCarUseCase
import br.com.fiap.catalogcar.domain.use_case.GetAllCarsUseCase
import br.com.fiap.catalogcar.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class CarListViewModel @Inject constructor(
    private val getAllCarsUseCase: GetAllCarsUseCase,
    private val delCarUseCase: DelCarUseCase,
) : ViewModel() {
    private val _state = mutableStateOf(CarListState())
    val state: State<CarListState> = _state

    init {
        getCar()
    }

     fun getCar() {
        getAllCarsUseCase().onEach { result ->
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
    ) {
        delCarUseCase(id).onEach { result ->
            when (result) {
                is Resource.Success -> viewModelScope.cancel()
                is Resource.Error -> _state.value =
                    CarListState(error = result.message ?: "An unexpected error occurred")
                is Resource.Loading -> _state.value =
                    CarListState(isLoading = true)
            }
        }.launchIn(viewModelScope)
    }
}