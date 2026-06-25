package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun OrganizadorHor(
    cambiarPantalla: (String) -> Unit
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Organizador de horarios")

        Text("Lunes:")
        Text("8:15 - Introduccion a la programacion")

        Text("Martes:")
        Text("9:45 - Fisica")

        Text("Miercoles:")
        Text("12:45 - Algebra I")

        Text("Jueves:")
        Text("14:15 - Calculo I")

        Text("Viernes:")
        Text("17:15 - Fisica Labo")
    }
}