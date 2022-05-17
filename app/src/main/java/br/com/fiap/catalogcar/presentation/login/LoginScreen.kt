package br.com.fiap.catalogcar.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.fiap.catalogcar.R
import br.com.fiap.catalogcar.presentation.components.CarTopBar

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navHostController: NavHostController,
) {

    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.state.value

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                CarTopBar(title = stringResource(id = R.string.login))
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
                        value = userEmail,
                        isError = state.error.isNotBlank(),
                        label = {
                            Text(text = "User")
                        },
                        onValueChange = {
                            userEmail = it
                        }
                    )

                    OutlinedTextField(
                        modifier = Modifier.fillMaxWidth(),
                        visualTransformation = PasswordVisualTransformation(),
                        value = userPassword,
                        isError = state.error.isNotBlank(),
                        label = {
                            Text(text = "Password")
                        },
                        onValueChange = {
                            userPassword = it
                        }
                    )

                    Button(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(50.dp),
                        enabled = userEmail.isNotEmpty().and(userPassword.isNotEmpty()),
                        content = {
                            Text(text = "Login")
                        },
                        onClick = {
                            viewModel.login(userEmail, userPassword, navHostController)
                        }
                    )
                    Text(text = state.error, color = Color.Red)
                }
            )
        }
    )
}