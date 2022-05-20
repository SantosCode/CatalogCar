package br.com.fiap.catalogcar.presentation.car_list

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.fiap.catalogcar.R
import br.com.fiap.catalogcar.presentation.car_form.components.AddCarFloatingButton
import br.com.fiap.catalogcar.presentation.car_list.components.CarItem
import br.com.fiap.catalogcar.presentation.components.CarTopBar
import br.com.fiap.catalogcar.utils.Constants

@Composable
fun CarListScreen(
    viewModel: CarListViewModel = hiltViewModel(),
    navHostController: NavHostController
) {
    val state = viewModel.state.value
    Scaffold(
        topBar = { CarTopBar(title = stringResource(id = R.string.list_car),
            onClick = {navHostController.navigate(Constants.LOGIN_VIEW)},
            isVisibleNav = true)},
        floatingActionButton = {
           AddCarFloatingButton(navHostController = navHostController)
        }, content = {
            Box(modifier = Modifier.fillMaxSize()) {
                LazyColumn(Modifier.fillMaxSize()) {
                    items(state.cars) { car ->
                        CarItem(car = car, onclickDelete = { car.id?.let { id ->
                            viewModel.deleteCar(id, navHostController)
                        } })
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