package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import com.raccy.racplanner.viewmodel.DetalleCarreraVM

@Composable
fun DetalleCarrera(
    cambiarPantalla: (String) -> Unit,
    codigo: String
) {
    val viewModel: DetalleCarreraVM = viewModel()
    val state by viewModel.state.collectAsState()

    LaunchedEffect(codigo) {
        viewModel.cargarCarrera(codigo)
    }
    LazyColumn(modifier = Modifier.padding(20.dp)) {
        item {
            Text("RacPlanner")
            Text("Detalle de la carrera")
        }
        if (state == null) {
            item {
                Text("Cargando...")
            }
        } else {
            item {
                Text("Codigo: ${state?.code ?: ""}")
                Text("Nombre: ${state?.name ?: ""}")
                Text("Semestre: ${state?.semester ?: ""}")
            }
            state?.levels?.forEach { nivel ->
                item {
                    Text("Nivel ${nivel.code}")
                }
                items(nivel.subjects) { materia ->
                    Text(
                        text = "Materia: ${materia.name}",
                        modifier = Modifier.padding(start = 16.dp)
                    )
                    materia.groups.forEach { grupo ->
                        Text(
                            text = "Grupo: ${grupo.code} - ${grupo.teacher}",
                            modifier = Modifier.padding(start = 32.dp)
                        )
                    }
                }
            }
        }
    }
}