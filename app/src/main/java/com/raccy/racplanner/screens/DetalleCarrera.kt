package com.raccy.racplanner.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raccy.racplanner.RacPlannerApp
import com.raccy.racplanner.network.response.GroupResponse
import com.raccy.racplanner.network.response.NivelResponse
import com.raccy.racplanner.network.response.SubjectResponse
import com.raccy.racplanner.repository.DetalleCarreraRepository
import com.raccy.racplanner.state.HorarioState
import com.raccy.racplanner.utils.formatearDiaGrilla
import com.raccy.racplanner.utils.formatearHora
import com.raccy.racplanner.viewmodel.DetalleCarreraVM
import com.raccy.racplanner.viewmodel.DetalleCarreraVMFactory

@Composable
fun DetalleCarrera(
    codigo: String,
    horarioState: HorarioState
) {
    val context = LocalContext.current
    val app = context.applicationContext as RacPlannerApp
    val factory = remember {
        DetalleCarreraVMFactory(
            DetalleCarreraRepository(
                app.detalleCarreraCacheRepository
            )
        )
    }
    val viewModel: DetalleCarreraVM = viewModel(
        factory = factory
    )
    val state by viewModel.state.collectAsState()
    val error by viewModel.error.collectAsState()
    val gruposSeleccionados by horarioState.gruposSeleccionados.collectAsState()

    LaunchedEffect(codigo) {
        viewModel.cargarCarrera(codigo)
    }

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
            text = state?.name?: "",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = state?.semester ?: "",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier
                .weight(1f)
                .fillMaxWidth()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            if (error != null) {
                item {
                    Text(
                        text = error!!,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            } else if (state == null) {
                item {
                    Text("Cargando...")
                }
            } else {
                items(state?.levels ?: emptyList()) { nivel ->
                    NivelItem(
                        codigoCarrera = codigo,
                        nombreCarrera = state?.name ?: "",
                        nivel = nivel,
                        horarioState = horarioState,
                        gruposSeleccionados = gruposSeleccionados
                    )
                }
            }
        }
    }
}

@Composable
fun NivelItem(
    codigoCarrera: String,
    nombreCarrera: String,
    nivel: NivelResponse,
    horarioState: HorarioState,
    gruposSeleccionados: Map<Int, GroupResponse>
) {
    var expandido by remember { mutableStateOf(false) }

    val nivelSeleccionado = nivel.subjects.any {
        gruposSeleccionados.containsKey(it.code)
    }

    Column( modifier = Modifier.animateContentSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { expandido = !expandido }
                .padding(vertical = 6.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = if (expandido) {
                    Icons.Default.KeyboardArrowDown
                } else {
                    Icons.AutoMirrored.Filled.KeyboardArrowRight
                },
                contentDescription = null,
                tint = if (nivelSeleccionado) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
            Spacer(modifier = Modifier.height(4.dp))
            Text(
                text = "Nivel ${nivel.code}",
                style = MaterialTheme.typography.titleMedium,
                color = if (nivelSeleccionado) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
        if (expandido) {
            nivel.subjects.forEach { materia ->
                MateriaItem(
                    codigoCarrera = codigoCarrera,
                    nombreCarrera = nombreCarrera,
                    materia = materia,
                    horarioState = horarioState,
                    gruposSeleccionados = gruposSeleccionados
                )
            }
        }
    }
}

@Composable
fun MateriaItem(
    codigoCarrera: String,
    nombreCarrera: String,
    materia: SubjectResponse,
    horarioState: HorarioState,
    gruposSeleccionados: Map<Int, GroupResponse>
) {
    var expandido by remember {
        mutableStateOf(false)
    }

    val seleccionada = gruposSeleccionados.containsKey(materia.code)

    Column( modifier = Modifier.animateContentSize()) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable{ expandido = !expandido }
                .padding(start = 24.dp, top = 4.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
           Icon(
               imageVector = if (expandido) {
                   Icons.Default.KeyboardArrowDown
               } else {
                   Icons.AutoMirrored.Filled.KeyboardArrowRight
               },
               contentDescription = null,
               tint = if (seleccionada) {
                   MaterialTheme.colorScheme.primary
               } else {
                   MaterialTheme.colorScheme.onSurface
               }
           )
            Text(
                text = materia.name,
                style = MaterialTheme.typography.titleSmall,
                color = if (seleccionada) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
        }
        if (expandido) {
            materia.groups.forEachIndexed { index, grupo ->
                GrupoItem(
                    codigoCarrera = codigoCarrera,
                    nombreCarrera = nombreCarrera,
                    materia = materia,
                    grupo = grupo,
                    horarioState = horarioState,
                    gruposSeleccionados = gruposSeleccionados
                )
                if (index < materia.groups.lastIndex) {
                    Spacer(modifier = Modifier.height(6.dp))
                }
            }
        }
    }
}

@Composable
fun GrupoItem(
    codigoCarrera: String,
    nombreCarrera: String,
    materia: SubjectResponse,
    grupo: GroupResponse,
    horarioState: HorarioState,
    gruposSeleccionados: Map<Int, GroupResponse>
) {
    val seleccionado = gruposSeleccionados[materia.code]?.code == grupo.code

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{
                horarioState.seleccionarGrupo(
                    codigoCarrera = codigoCarrera,
                    nombreCarrera = nombreCarrera,
                    codigoMateria = materia.code,
                    nombreMateria = materia.name,
                    grupo = grupo
                )
            }
            .padding(start = 56.dp, top = 4.dp, bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = seleccionado,
            onClick = null
        )
        Column(modifier = Modifier.padding(start = 8.dp)) {
            Text(
                text = "Grupo ${grupo.code}",
                style = MaterialTheme.typography.bodyLarge,
                color = if (seleccionado) {
                    MaterialTheme.colorScheme.primary
                } else {
                    MaterialTheme.colorScheme.onSurface
                }
            )
            Text(
                text = grupo.teacher,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
            Spacer(modifier = Modifier.height(4.dp))

            grupo.schedule.forEach { horario ->
                Text(
                    text = "${formatearDiaGrilla(horario.day)} · ${formatearHora(horario.start)} - ${formatearHora(horario.end)}",
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Spacer(modifier = Modifier.height(2.dp))
            }
        }
    }
}