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

@Composable
fun CarFormScreen(
    viewModel: CarFormViewMode = hiltViewModel(),
    navHostController: NavHostController,
) {

    var model by remember { mutableStateOf("") }
    var manufacture by remember { mutableStateOf("") }
    var year by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.state.value

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                CarTopBar(title = stringResource(id = R.string.add_car),
                    onClick = {navHostController.navigate("carList")},
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
                            keyboardType = KeyboardType.Text
                        ),
                        label = {
                            Text(text = stringResource(id = R.string.model))
                        },
                        onValueChange = {
                            model = it
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        value = manufacture,
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text
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
                            year = it
                        }
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        content = {
                            Text(text = stringResource(id = R.string.add))
                        },
                        onClick = {
                            viewModel.addCar(model, manufacture, year.toInt(), navHostController)
                        }
                    )
                    Text(text = state.error, color = Color.Red)
                }
            )
        }
    )
}