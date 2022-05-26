package br.com.fiap.catalogcar.presentation.car_form.components

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.FloatingActionButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import br.com.fiap.catalogcar.ui.theme.CatalogCarTheme
import br.com.fiap.catalogcar.ui.theme.Shapes

@Composable
fun AddCarFloatingButton(
    onClick: (() -> Unit),
) {
    FloatingActionButton(
        onClick = onClick,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        shape = CircleShape,
        elevation = FloatingActionButtonDefaults.elevation(8.dp)
    ) {
        Icon(
            imageVector = Icons.Default.Add,
            contentDescription = ""
        )
    }
}

@Preview
@Composable
fun PreviewAddCar() {
    CatalogCarTheme {
        AddCarFloatingButton(onClick = {})
    }
}