package br.com.fiap.catalogcar.presentation.components

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
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

@Composable
fun CarTopBar(title: String, onClick: () -> Unit = {}, isVisibleNav: Boolean = false) {

    val activity = (LocalContext.current as? Activity)
    val colorBackground = if (isSystemInDarkTheme()) Color.Black
    else Color.White

    TopAppBar(
        backgroundColor = colorBackground,
        elevation = 2.dp,
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