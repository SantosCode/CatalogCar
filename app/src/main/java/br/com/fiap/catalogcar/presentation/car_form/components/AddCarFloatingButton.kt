package br.com.fiap.catalogcar.presentation.car_form.components

import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import br.com.fiap.catalogcar.presentation.car_form.CarFormViewMode

@Composable
fun AddCarFloatingButton(
    navHostController: NavHostController
) {
    FloatingActionButton(
        onClick = { navHostController.navigate("carForm") },
        backgroundColor = MaterialTheme.colors.primary
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ""
        )
    }
}