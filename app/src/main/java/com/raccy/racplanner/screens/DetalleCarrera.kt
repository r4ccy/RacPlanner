package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun DetalleCarrera(
    cambiarPantalla: (String) -> Unit,
    codigo: String
) {
    Column( modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Detalle de la carrera")

        Text("Codigo: 123-1")
        Text("Nombre: Ing. Informatica")
        Text("Semestre: Primer semestre")
        Text("Duracion: 10 Semestres")
    }
}