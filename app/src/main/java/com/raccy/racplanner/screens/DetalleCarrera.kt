package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.selection.selectable
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import com.raccy.racplanner.state.HorarioState
import com.raccy.racplanner.viewmodel.DetalleCarreraVM
import com.raccy.racplanner.utils.formatearDia
import com.raccy.racplanner.utils.formatearHora

@Composable
fun DetalleCarrera(
    cambiarPantalla: (String) -> Unit,
    codigo: String,
    horarioState: HorarioState
) {
    val viewModel: DetalleCarreraVM = viewModel()
    val state by viewModel.state.collectAsState()
    val gruposSeleccionados by viewModel.gruposSeleccionados.collectAsState()

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
                        Row (
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 32.dp)
                                .selectable(
                                    selected = gruposSeleccionados[materia.code]?.code == grupo.code,
                                    onClick = {
                                        viewModel.seleccionarGrupo(
                                            materia.code,
                                            grupo
                                        )
                                        horarioState.agregarGrupo(
                                            materia.name,
                                            grupo
                                        )
                                    }
                                ),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            RadioButton (
                                selected = gruposSeleccionados[materia.code]?.code == grupo.code,
                                onClick = null
                            )
                            Text(
                                text = "Grupo: ${grupo.code} - ${grupo.teacher}"
                            )
                        }
                        grupo.schedule.forEach { horario ->
                            Text(
                                text = "${formatearDia(horario.day)} ${formatearHora(horario.start)} - ${formatearHora(horario.end)}",
                                modifier = Modifier.padding(start = 48.dp)
                            )
                        }
                    }
                }
            }
        }
    }
}