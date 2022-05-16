package br.com.fiap.catalogcar.data.local.datasource

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ActivityContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class StoreUser @Inject constructor(@ActivityContext private val context: Context) {

    val getAuth: Flow<String> = context.dataStore.data
        .map { preferences ->
            preferences[AUTH_API].orEmpty()
        }

    suspend fun saveUserPassword(auth: String) {
        context.dataStore.edit { preferences ->
            preferences[AUTH_API] = auth
        }
    }

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user")
        val AUTH_API = stringPreferencesKey("Authorization")
    }
}