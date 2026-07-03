package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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
                modifier = Modifier.weight(1f),
                contentPadding = PaddingValues(bottom = 16.dp),
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
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {
                            Column( modifier = Modifier.padding(16.dp)
                            ) {
                                Text(
                                    text = "${formatearHora(evento.inicio)} - ${formatearHora(evento.fin)}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = "${evento.materia} · ${evento.aula}",
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Text(
                                    text = evento.docente,
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Spacer(modifier = Modifier.height(8.dp))
                                Text(
                                    text = if (evento.esTeoria) {
                                        "Teoría"
                                    } else {
                                        "Auxiliatura"
                                    },
                                    style = MaterialTheme.typography.bodySmall
                                )
                                if (evento.tieneColision) {
                                    Spacer(modifier = Modifier.height(6.dp))
                                    Text(
                                        text = "Choque de horario",
                                        style = MaterialTheme.typography.bodySmall,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}