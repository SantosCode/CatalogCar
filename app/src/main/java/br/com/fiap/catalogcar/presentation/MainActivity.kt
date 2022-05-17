package br.com.fiap.catalogcar.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.datastore.preferences.preferencesDataStore
import androidx.navigation.compose.rememberNavController
import br.com.fiap.catalogcar.data.local.datasource.StoreUser
import br.com.fiap.catalogcar.presentation.car_list.CarListScreen
import br.com.fiap.catalogcar.presentation.components.NavigationComponent
import br.com.fiap.catalogcar.ui.theme.CatalogCarTheme
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CatalogCarTheme {
                Surface(modifier = Modifier
                    .fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val navController = rememberNavController()
                    NavigationComponent(navController)
                }
            }
        }
    }
}

@Preview
@Composable
fun DefaultPreview() {
    CatalogCarTheme {
        Surface(modifier = Modifier
            .fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            val navController = rememberNavController()
            CarListScreen(navHostController = navController)
        }
    }
}