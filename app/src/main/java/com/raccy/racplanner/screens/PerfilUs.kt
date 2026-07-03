package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier

@Composable
fun PerfilUs(
    cambiarPantalla: (String) -> Unit
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Perfil")

        Text("Nombre:")
        Text("Anne Heart")

        Text("Carrera:")
        Text("Ingeniería Informática")

        Text("Semestre:")
        Text("Primer semestre")

        Text("Correo:")
        Text("anne.heart@umss.edu.bo")
    }
}