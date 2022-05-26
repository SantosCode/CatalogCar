package br.com.fiap.catalogcar.presentation.login

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import br.com.fiap.catalogcar.R
import br.com.fiap.catalogcar.presentation.components.CarTopBar
import br.com.fiap.catalogcar.ui.theme.CatalogCarTheme

@Composable
fun LoginScreen(
    viewModel: LoginViewModel = hiltViewModel(),
    navigateToCarList: () -> Unit,
) {

    var userEmail by remember { mutableStateOf("") }
    var userPassword by remember { mutableStateOf("") }

    val snackbarHostState = remember { SnackbarHostState() }
    val state = viewModel.state.value

    Scaffold(
        scaffoldState = rememberScaffoldState(snackbarHostState = snackbarHostState),
        topBar = {
            Column(modifier = Modifier.fillMaxWidth()) {
                CarTopBar(title = stringResource(id = R.string.login_title))
            }
        }
    ) {
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
                        Text(text = stringResource(id = R.string.user))
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
                        Text(text = stringResource(id = R.string.password))
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
                        Text(text = stringResource(id = R.string.login))
                    },
                    onClick = {
                        viewModel.login(userEmail, userPassword)
                        if (state.auth != null) {
                            navigateToCarList()
                        }
                    }
                )
                Text(text = state.error, color = Color.Red)
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun DefaultPreview() {
    CatalogCarTheme {
        Surface(
            modifier = Modifier
                .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginScreen(navigateToCarList = {})
        }
    }
}