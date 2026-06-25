package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Ajustes(
    cambiarPantalla: (String) -> Unit
) {
    Column(modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Ajustes")

        Text("Tema:")
        Text("Claro")

        Text("Idioma:")
        Text("Español")

        Text("Notificaciones:")
        Text("Activadas")
    }
}