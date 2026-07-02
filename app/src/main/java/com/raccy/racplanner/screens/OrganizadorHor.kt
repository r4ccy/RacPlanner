package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
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
    Column(modifier = Modifier.padding(20.dp)) {
        Text("RacPlanner")
        Text("Organizador de Horario")
        if (eventos.isEmpty()) {
            Text("No hay materias Seleccionadas")
        } else {
            LazyColumn (modifier = Modifier.padding(20.dp)) {
                eventosPorDia.forEach { (dia, eventosDelDia) ->
                    item {
                        Text(formatearDia(dia))
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