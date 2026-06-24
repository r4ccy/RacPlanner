package com.raccy.racplanner

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.raccy.racplanner.screens.BuscadorCarrera
import com.raccy.racplanner.screens.OrganizadorHor
import com.raccy.racplanner.screens.DetalleCarrera
import com.raccy.racplanner.screens.PerfilUs
import com.raccy.racplanner.screens.Ajustes
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import com.raccy.racplanner.ui.theme.RacPlannerTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var pantalla by remember {
                mutableStateOf("buscador")
            }
            Column {
                when (pantalla) {
                    "buscador" -> BuscadorCarrera(cambiarPantalla = { pantalla = it })
                    "organizador" -> OrganizadorHor(cambiarPantalla = { pantalla = it })
                    "detalle" -> DetalleCarrera(cambiarPantalla = { pantalla = it })
                    "perfil" -> PerfilUs(cambiarPantalla = { pantalla = it })
                    "ajustes" -> Ajustes(cambiarPantalla = { pantalla = it })
                }
                Column {
                    Button(onClick = { pantalla = "buscador" }
                    ) {
                        Text("Buscar")
                    }
                    Button(onClick = { pantalla = "organizador" }
                    ) {
                        Text("Organizar")
                    }
                    Button(onClick = { pantalla = "detalle" }
                    ) {
                        Text("Detalle")
                    }
                    Button(onClick = { pantalla = "perfil" }
                    ) {
                        Text("Perfil")
                    }
                    Button(onClick = { pantalla = "ajustes" }
                    ) {
                        Text("Ajustes")
                    }
                }
            }
            }
        }
    }

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
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