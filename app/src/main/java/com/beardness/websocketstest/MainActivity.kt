package com.beardness.websocketstest

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.os.Bundle
import android.os.Vibrator
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.beardness.websocketstest.screen.MainScreen
import com.beardness.websocketstest.screen.MainScreenViewModel
import com.beardness.websocketstest.ui.theme.WebSocketsTestTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

private const val DATA_STORE_NAME = "websocket-app-datastore"
private const val DATA_STORE_STRING_KEY_URL = "DATA_STORE_STRING_KEY_URL"

class MainActivity : ComponentActivity() {

    private val scope = CoroutineScope(context = Dispatchers.Main)

    private val viewModel: MainScreenViewModel = MainScreenViewModel()

    private var connectionManager: ConnectivityManager? = null
    private var vibrator: Vibrator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupActivity()
        setupUrl()
        listenConnectionManager()
        listenUrl()
        listenFailure()
        compose()
    }

    override fun onStop() {
        super.onStop()
        viewModel.silentDisconnect()
    }

    private fun setupActivity() {
        connectionManager = getSystemService(ConnectivityManager::class.java)
        vibrator = getSystemService(Vibrator::class.java)
    }

    private fun setupUrl() {
        scope.launch {
            val urlFromDataStore =
                wsDataStore
                    .data
                    .map { preferences ->
                        val urlKey = stringPreferencesKey(name = DATA_STORE_STRING_KEY_URL)
                        preferences[urlKey] ?: ""
                    }
                    .firstOrNull()
                    ?: return@launch

           viewModel.url(update = urlFromDataStore)
        }
    }

    private fun listenConnectionManager() {
        connectionManager?.registerDefaultNetworkCallback(
            object : ConnectivityManager.NetworkCallback() {
                override fun onAvailable(network: Network) {
                    super.onAvailable(network)
                    viewModel.internet(availability = true)
                }

                override fun onLost(network: Network) {
                    super.onLost(network)
                    viewModel.internet(availability = false)
                }
            }
        )
    }

    private fun listenUrl() {
        scope.launch {
            viewModel.url.collect { url ->
                if (url.isEmpty()) {
                    return@collect
                }

                val urlKey = stringPreferencesKey(name = DATA_STORE_STRING_KEY_URL)

                wsDataStore.edit { mutablePreferences ->
                    mutablePreferences[urlKey] = url
                }
            }
        }
    }

    private fun listenFailure() {
        scope.launch {
            viewModel.failure.collect { failureDto ->
                failureDto ?: return@collect

                toast(text = failureDto.message)
                haptic()
            }
        }
    }

    private fun compose() {
        setContent {
            WebSocketsTestTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier
                        .fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen(viewModel = viewModel)
                }
            }
        }
    }

    private fun toast(text: String) {
        Toast.makeText(baseContext, text, Toast.LENGTH_SHORT).show()
    }

    private fun haptic() {
        vibrator?.vibrate(50)
    }

    private val Context.wsDataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
}