package br.com.fiap.catalogcar.presentation.car_form

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.fiap.catalogcar.R
import br.com.fiap.catalogcar.presentation.components.CarTopBar
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.runBlocking

@DelicateCoroutinesApi
@Composable
fun CarFormScreen(
    viewModel: CarFormViewMode = hiltViewModel(),
    navigateToCarList: () -> Unit,
    id: Long? = null,
) {

    var model by remember { mutableStateOf("") }
    var manufacture by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    val state = viewModel.state.value
    val snackbarHostState = remember { SnackbarHostState() }

    id?.let {
        runBlocking {
            val job = viewModel.editCar(it)
            if (state.car != null) {
                job.cancel()
            }
        }
    }

    state.car?.let { carDto ->
        model = carDto.model
        manufacture = carDto.manufacture
        year = carDto.year.toString()
    }

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                CarTopBar(title = stringResource(id = R.string.add_car),
                    onClick = navigateToCarList,
                    isVisibleNav = true)
                if (state.isLoading) {
                    LinearProgressIndicator(modifier = Modifier.fillMaxWidth())
                }
            }
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp),
                verticalArrangement = Arrangement.spacedBy(18.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                content = {
                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = model,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            autoCorrect = true
                        ),
                        label = {
                            Text(text = stringResource(id = R.string.model_car))
                        },
                        onValueChange = {
                            model = it
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = manufacture,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            autoCorrect = true
                        ),
                        label = {
                            Text(text = stringResource(id = R.string.manufacture))
                        },
                        onValueChange = {
                            manufacture = it
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = year,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Number
                        ),
                        label = {
                            Text(text = stringResource(id = R.string.year))
                        },
                        onValueChange = {
                            year = manageLength(it)
                        }
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        content = {
                            Text(text = stringResource(id = R.string.add))
                        },
                        enabled = model.isNotEmpty()
                            .and(manufacture.isNotEmpty())
                            .and(year.isNotEmpty()),
                        onClick = {
                            viewModel.addCar(
                                state.car?.id,
                                model,
                                manufacture,
                                year.toInt()
                            )
                            navigateToCarList()
                        }
                    )
                    Text(text = state.error, color = Color.Red)
                }
            )
        }
    )
}

private fun manageLength(input: String) = if (input.length > 4) input.substring(0..3) else input