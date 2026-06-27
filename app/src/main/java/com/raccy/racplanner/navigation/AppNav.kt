package com.raccy.racplanner.navigation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.raccy.racplanner.screens.Ajustes
import com.raccy.racplanner.screens.BuscadorCarrera
import com.raccy.racplanner.screens.DetalleCarrera
import com.raccy.racplanner.screens.OrganizadorHor
import com.raccy.racplanner.screens.PerfilUs

@Composable
fun AppNav() {

    var pantalla by remember {
        mutableStateOf(BUSCADOR)
    }

    var codCarrera by remember {
        mutableStateOf("")
    }

    Scaffold { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {

            Column(
                modifier = Modifier.weight(1f)
            ) {

                when (pantalla) {

                    BUSCADOR -> BuscadorCarrera(
                        cambiarPantalla = { pantalla = it },
                        seleccionarCarrera = { codigo ->
                            codCarrera = codigo
                        }
                    )

                    ORGANIZADOR ->
                        OrganizadorHor(
                            cambiarPantalla = { pantalla = it }
                        )

                    DETALLE ->
                        DetalleCarrera(
                            cambiarPantalla = { pantalla = it },
                            codigo = codCarrera
                        )

                    PERFIL ->
                        PerfilUs(
                            cambiarPantalla = { pantalla = it }
                        )

                    AJUSTES ->
                        Ajustes(
                            cambiarPantalla = { pantalla = it }
                        )
                }
            }

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                Button(onClick = { pantalla = BUSCADOR }) {
                    Text("Buscar")
                }

                Button(onClick = { pantalla = ORGANIZADOR }) {
                    Text("Organizar")
                }

                Button(onClick = { pantalla = PERFIL }) {
                    Text("Perfil")
                }

                Button(onClick = { pantalla = AJUSTES }) {
                    Text("Ajustes")
                }

            }

        }

    }
}