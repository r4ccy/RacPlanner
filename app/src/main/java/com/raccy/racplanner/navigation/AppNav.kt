package com.raccy.racplanner.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarMonth
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
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
import com.raccy.racplanner.state.HorarioState

@Composable
fun AppNav() {

    var pantalla by remember {
        mutableStateOf(BUSCADOR)
    }

    var codCarrera by remember {
        mutableStateOf("")
    }

    val horarioState = remember {
        HorarioState()
    }

    Scaffold (
        bottomBar = {
            NavigationBar (
                modifier = Modifier.height(88.dp),
                tonalElevation = 0.dp
            ) {
                NavigationBarItem(
                    selected = pantalla == BUSCADOR,
                    onClick = { pantalla = BUSCADOR },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Search,
                            contentDescription = "Buscar"
                        )
                    },
                    label = {
                        Text("Buscar")
                    }
                )
                NavigationBarItem(
                    selected = pantalla == ORGANIZADOR,
                    onClick = { pantalla = ORGANIZADOR },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.CalendarMonth,
                            contentDescription = "Organizar"
                        )
                    },
                    label = {
                        Text("Organizar")
                    }
                )
                NavigationBarItem(
                    selected = pantalla == PERFIL,
                    onClick = { pantalla = PERFIL },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Person,
                            contentDescription = "Perfil"
                        )
                    },
                    label = {
                        Text("Perfil")
                    }
                )

                NavigationBarItem(
                    selected = pantalla == AJUSTES,
                    onClick = { pantalla = AJUSTES },
                    icon = {
                        Icon(
                            imageVector = Icons.Default.Settings,
                            contentDescription = "Ajustes"
                        )
                    },
                    label = {
                        Text("Ajustes")
                    }
                )
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
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
                        cambiarPantalla = { pantalla = it },
                        horarioState = horarioState
                    )

                DETALLE ->
                    DetalleCarrera(
                        cambiarPantalla = { pantalla = it },
                        codigo = codCarrera,
                        horarioState = horarioState
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
    }
}