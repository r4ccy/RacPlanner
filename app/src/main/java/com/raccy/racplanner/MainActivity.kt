package com.raccy.racplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raccy.racplanner.datastore.SettingsDataStore
import com.raccy.racplanner.navigation.AppNav
import com.raccy.racplanner.ui.theme.RacPlannerTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        enableEdgeToEdge()

        setContent {

            val temaOscuro by SettingsDataStore
                .temaOscuro(this)
                .collectAsState(initial = false)

            RacPlannerTheme(
                darkTheme = temaOscuro
            ) {
                AppNav()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    androidx.compose.material3.Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RacPlannerTheme {
        Greeting("Android")
    }
}