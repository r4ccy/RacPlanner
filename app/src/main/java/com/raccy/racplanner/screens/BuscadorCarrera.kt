package com.raccy.racplanner.screens

import androidx.lifecycle.viewmodel.compose.viewModel
import com.raccy.racplanner.viewmodel.BuscadorVM
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BuscadorCarrera(
    cambiarPantalla: (String) -> Unit,
    seleccionarCarrera: (String) -> Unit
) {

    val viewModel: BuscadorVM = viewModel()
    val state by viewModel.state.collectAsState()

    Column(modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Buscador de carreras")
        Text("Carreras: ${state.carreras.size}")

        LazyColumn {
            items(state.carreras) { carrera ->
                Text(carrera.nombre)
                Button(
                    onClick = {
                        seleccionarCarrera(carrera.codigo.toString())
                        cambiarPantalla("detalle")
                    }
                ) { Text("Ver detalles") }
            }
        }
    }
}