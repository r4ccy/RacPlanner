package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.getValue
import androidx.compose.runtime.collectAsState
import com.raccy.racplanner.state.HorarioState
import com.raccy.racplanner.utils.formatearDia
import com.raccy.racplanner.utils.formatearHora

@Composable
fun OrganizadorHor(
    cambiarPantalla: (String) -> Unit,
    horarioState: HorarioState
) {
    val eventos by horarioState.eventos.collectAsState()
    val eventosPorDia = eventos.groupBy { it.dia }
    val ordenDias = listOf(
        "LU",
        "MA",
        "MI",
        "JU",
        "VI",
        "SA"
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        Text(
            text = "RacPlanner",
            style = MaterialTheme.typography.headlineMedium
        )
        Text(
            text = "Horario",
            style = MaterialTheme.typography.headlineLarge
        )

        Button(
            onClick = {
                horarioState.vaciarHorario()
            }
        ) { Text("Vaciar horario") }

        if (eventos.isEmpty()) {
            Text("No hay materias en el horario")
        } else {
            LazyColumn (
                modifier = Modifier.padding(20.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                ordenDias.forEach { dia ->
                    val eventosDelDia = eventosPorDia[dia]?: return@forEach

                    item {
                        HorizontalDivider()
                        Text(
                            text = "${formatearDia(dia)} · ${eventosDelDia.size} ${
                                if (eventosDelDia.size == 1) "clase" else "clases"
                            }",
                            style = MaterialTheme.typography.titleMedium
                        )
                    }
                    items(eventosDelDia) { evento ->
                        Text("${formatearHora(evento.inicio)} - ${formatearHora(evento.fin)}")
                        Text(evento.materia)
                        Text(evento.docente)
                        Text(
                            text = if (evento.esTeoria)
                            "Teórica"
                            else
                            "Auxiliatura"
                        )
                        if (evento.tieneColision) {
                            Text("Conflicto de horario")
                        }
                    }
                }
            }
        }
    }
}