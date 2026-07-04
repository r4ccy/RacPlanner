package com.raccy.racplanner.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.raccy.racplanner.model.EventoHorario
import com.raccy.racplanner.utils.horasGrilla
import com.raccy.racplanner.utils.ordenDias
import com.raccy.racplanner.utils.formatearDiaGrilla
import com.raccy.racplanner.utils.formatearHora

@Composable
fun HorarioGrilla(
    eventos: List<EventoHorario>
) {

    val diasClases = ordenDias.filter { dia ->
        eventos.any { it.dia == dia }
    }
    val horasClases = horasGrilla.filter { hora ->
        eventos.any { it.inicio == hora }
    }
    val horizontalState = rememberScrollState()
    val verticalState = rememberScrollState()

    Column {
        Row {
            Box(
                modifier = Modifier
                    .width(80.dp)
                    .height(40.dp)
                    .border(
                        1.dp,
                        MaterialTheme.colorScheme.outline
                    ),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Hora",
                    style = MaterialTheme.typography.labelMedium
                )
            }
            Row(
                modifier = Modifier.horizontalScroll(horizontalState)
            ) {
                diasClases.forEach { dia ->
                    Box(
                        modifier = Modifier
                            .width(130.dp)
                            .height(40.dp)
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.outline
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = formatearDiaGrilla(dia),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier.verticalScroll(verticalState)
        ) {
            Column {
                horasClases.forEach { hora ->
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .height(88.dp)
                            .border(
                                1.dp,
                                MaterialTheme.colorScheme.outline
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Text(
                            text = formatearHora(hora),
                            style = MaterialTheme.typography.labelMedium
                        )
                    }
                }
            }

            Row(
                modifier = Modifier.horizontalScroll(horizontalState)
            ) {
                Column {
                    horasClases.forEach { hora ->
                        Row {
                            diasClases.forEach { dia ->
                                val eventosCelda = eventos.filter {
                                    it.dia == dia && it.inicio == hora
                                }
                                Box(
                                    modifier = Modifier
                                        .width(130.dp)
                                        .height(88.dp)
                                        .border(
                                            1.dp,
                                            MaterialTheme.colorScheme.outline
                                        )
                                ) {
                                    if (eventosCelda.isNotEmpty()) {
                                        Column(
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(4.dp),
                                            verticalArrangement = Arrangement.Top,
                                            horizontalAlignment = Alignment.CenterHorizontally
                                        ) {
                                            val modoCompacto = eventosCelda.size > 3
                                            val eventosVisibles = eventosCelda.take(2)

                                            eventosVisibles.forEachIndexed { index, evento ->
                                                Text(
                                                    text = evento.materia,
                                                    maxLines = if (modoCompacto) 1 else 2,
                                                    overflow = TextOverflow.Ellipsis,
                                                    fontSize = if (modoCompacto) 10.sp else 11.sp,
                                                    lineHeight = if (modoCompacto) 8.sp else 10.sp,
                                                    textAlign = TextAlign.Center,
                                                    color = if (evento.tieneColision) {
                                                        MaterialTheme.colorScheme.error
                                                    } else {
                                                        MaterialTheme.colorScheme.onSurface
                                                    }
                                                )
                                                Text(
                                                    text = evento.aula,
                                                    fontSize = if (modoCompacto) 10.sp else 11.sp,
                                                    lineHeight = 6.sp,
                                                    textAlign = TextAlign.Center,
                                                    color = if (evento.tieneColision) {
                                                        MaterialTheme.colorScheme.error
                                                    } else {
                                                        MaterialTheme.colorScheme.onSurfaceVariant
                                                    }
                                                )
                                                if (index == eventosVisibles.lastIndex && eventosCelda.size > 2) {
                                                    Text(
                                                        text = "+${eventosCelda.size - 2}",
                                                        fontSize = if (modoCompacto) 10.sp else 11.sp,
                                                        lineHeight = 6.sp,
                                                        style = MaterialTheme.typography.labelSmall,
                                                        color = MaterialTheme.colorScheme.error
                                                    )
                                                }
                                                if (index < eventosVisibles.lastIndex) {
                                                    Spacer(modifier = Modifier.height(1.dp))
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}