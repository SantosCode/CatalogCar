package br.com.fiap.catalogcar.presentation.car_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import br.com.fiap.catalogcar.R
import br.com.fiap.catalogcar.presentation.car_form.components.AddCarFloatingButton
import br.com.fiap.catalogcar.presentation.car_list.components.CarItem
import br.com.fiap.catalogcar.presentation.components.CarTopBar

@Composable
fun CarListScreen(
    viewModel: CarListViewModel = hiltViewModel(),
    navigateToCarForm: () -> Unit,
    navigateToLogin: () -> Unit,
    navigateToCarEdit: (id: Long) -> Unit,
    navigateToCarList: () -> Unit
) {

    val state = viewModel.state.value

    LaunchedEffect(Unit ){
        viewModel.getCar()
    }

    Scaffold(
        topBar = {
            CarTopBar(title = stringResource(id = R.string.list_car),
                onClick = navigateToLogin,
                isVisibleNav = true)
        },
        floatingActionButton = {
            AddCarFloatingButton(
                onClick = navigateToCarForm
            )
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(state.cars) { car ->
                        CarItem(
                            car = car,
                            onClickEdit = {
                                car.id?.let { id ->
                                   navigateToCarEdit(id)
                                }
                            },
                            onclickDelete = {
                                car.id?.let { id ->
                                    viewModel.deleteCar(id)
                                    navigateToCarList()
                                }
                            })
                    }
                }
                if (state.error.isNotBlank()) {
                    Text(
                        text = state.error,
                        color = MaterialTheme.colors.error,
                        textAlign = TextAlign.Center,
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(horizontal = 20.dp)
                            .align(Alignment.Center)
                    )
                }
                if (state.isLoading) {
                    CircularProgressIndicator(modifier = Modifier
                        .align(Alignment.Center)
                    )
                }
            }
        })
}