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
    cambiarPantalla: (String) -> Unit,
    seleccionarCarrera: (String) -> Unit
) {

    Column( modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Buscador de carreras")

        Text("Ing. Informatica")
        Button(
            onClick = {
                seleccionarCarrera("123-1")
                cambiarPantalla("detalle") }
        ) { Text("Ver detalles")}

        Text("Ing. Electronica")
        Button(
            onClick = {
                seleccionarCarrera("123-2")
                cambiarPantalla("detalle") }
        ) { Text("Ver detalles")}

        Text("Ing. Mecatronica")
        Button(
            onClick = {
                seleccionarCarrera("123-3")
                cambiarPantalla("detalle") }
        ) { Text("Ver detalles")}

        Text("Ing. Industrial")
        Button(
            onClick = {
                seleccionarCarrera("123-4")
                cambiarPantalla("detalle") }
        ) { Text("Ver detalles")}

        Text("Ing. Civil")
        Button(
            onClick = {
                seleccionarCarrera("123-5")
                cambiarPantalla("detalle") }
        ) { Text("Ver detalles")}

        Text("Ing. Sistemas")
        Button(
            onClick = {
                seleccionarCarrera("123-6")
                cambiarPantalla("detalle") }
        ) { Text("Ver detalles")}
    }
}