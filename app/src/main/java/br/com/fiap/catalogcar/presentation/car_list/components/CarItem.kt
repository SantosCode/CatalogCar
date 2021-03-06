package br.com.fiap.catalogcar.presentation.car_list.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DeleteForever
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.fiap.catalogcar.R
import br.com.fiap.catalogcar.domain.model.Car
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation

@Composable
fun CarItem(
    car: Car,
    onclickDelete: () -> Unit = {},
    onClickEdit: () -> Unit = {},
) {
    Card(modifier = Modifier
        .clickable(onClick = onClickEdit)
        .fillMaxWidth()
        .padding(top = 8.dp, bottom = 16.dp, start = 4.dp, end = 4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(corner = CornerSize(16.dp))
    ) {
        Row(modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(modifier = Modifier
                .padding(2.dp),
                Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.car),
                    contentDescription = null,
                    modifier = Modifier.size(100.dp)
                )
            }
            Column(modifier = Modifier
                .padding(2.dp),
                Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.model_car),
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = car.model)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.manufacture),
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = car.manufacture)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Text(text = stringResource(id = R.string.year),
                        fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text(text = car.year.toString())
                }
            }
            Column(
                modifier = Modifier
                    .padding(2.dp),
                Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                FloatingActionButton(onClick = onclickDelete,
                    backgroundColor = MaterialTheme.colors.error,
                    modifier = Modifier.size(40.dp)) {
                    Icon(
                        imageVector = Icons.Default.DeleteForever,
                        contentDescription = "")
                }
            }
        }
    }
}