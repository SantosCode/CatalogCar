package br.com.fiap.catalogcar.presentation.components

import android.app.Activity
import android.service.autofill.OnClickAction
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowBack
import androidx.compose.material.icons.rounded.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import br.com.fiap.catalogcar.presentation.MainActivity
import com.google.firebase.auth.FirebaseAuth

@Composable
fun CarTopBar(title: String, onClick: () -> Unit = {}, isVisibleNav: Boolean = false) {

    val activity =(LocalContext.current as? Activity)

    TopAppBar(
        backgroundColor = Color.White,
        elevation = 1.dp,
        title = {
            Text(text = title)
        },
        navigationIcon = {
            if (isVisibleNav) {
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Rounded.ArrowBack,
                        contentDescription = null,
                    )
                }
            }
        },
        actions = {
            IconButton(onClick = { activity?.finish() }) {
                Icon(
                    imageVector = Icons.Rounded.ExitToApp,
                    contentDescription = null,
                )
            }
        }
    )
}