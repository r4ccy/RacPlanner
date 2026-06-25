package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun BuscadorCarrera(
    cambiarPantalla: (String) -> Unit
) {

    Column( modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Buscador de carreras")

        Text("123-1")
        Text("Ing. Informatica")

        Text("123-2")
        Text("Ing. Electronica")

        Text("123-3")
        Text("Ing. Mecatronica")

        Text("123-4")
        Text("Ing. Industrial")

        Text("123-5")
        Text("Ing. Civil")

        Text("123-6")
        Text("Ing. Sistemas")
    }
}