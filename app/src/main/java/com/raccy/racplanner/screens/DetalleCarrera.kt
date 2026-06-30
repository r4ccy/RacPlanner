package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
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
    Column( modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Detalle de la carrera")

        if (state == null) {
            Text("Cargando...")
        } else {
            Text("Codigo: ${state?.code ?: ""}")
            Text("Nombre: ${state?.name ?: ""}")
            Text("Semestre: ${state?.semester ?: ""}")
            state?.levels?.forEach { nivel -> Text("Nivel ${nivel.code}") }
        }
    }
}