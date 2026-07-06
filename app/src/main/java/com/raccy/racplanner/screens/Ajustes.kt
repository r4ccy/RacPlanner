package com.raccy.racplanner.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raccy.racplanner.RacPlannerApp
import com.raccy.racplanner.state.HorarioState
import com.raccy.racplanner.viewmodel.AjustesVM
import kotlinx.coroutines.launch

@Composable
fun Ajustes(
    horarioState: HorarioState
) {

    val viewModel: AjustesVM = viewModel()
    val temaOscuro by viewModel.temaOscuro.collectAsState()
    val context = LocalContext.current
    val app = context.applicationContext as RacPlannerApp
    val scope = rememberCoroutineScope()
    var mostrarAcercaDe by remember {
        mutableStateOf(false)
    }
    var mostrarConfirmacionLimpiar by remember {
        mutableStateOf(false)
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
            text = "Ajustes",
            style = MaterialTheme.typography.headlineLarge
        )

        Spacer(modifier = Modifier.height(20.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Palette,
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.primary
                )

                Spacer(modifier = Modifier.width(12.dp))

                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = "Tema oscuro",
                        style = MaterialTheme.typography.titleMedium
                    )

                    Text(
                        text = if (temaOscuro) "Activado" else "Desactivado",
                        style = MaterialTheme.typography.bodyMedium,
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                }

                Switch(
                    checked = temaOscuro,
                    onCheckedChange = {
                        viewModel.cambiarTema(it)
                    }
                )
            }
        }
        Spacer(modifier = Modifier.height(12.dp))
        OpcionAjuste(
            icono = Icons.Default.Delete,
            titulo = "Limpiar caché",
            valor = "Eliminar datos almacenados",
            tint = MaterialTheme.colorScheme.error,
            onClick = {
                mostrarConfirmacionLimpiar = true
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        OpcionAjuste(
            icono = Icons.Default.Refresh,
            titulo = "Refrescar datos",
            valor = "Actualizar carreras",
            tint = MaterialTheme.colorScheme.primary,
            onClick = {
                scope.launch {
                    app.carreraCacheRepository.limpiar()

                    Toast.makeText(
                        context,
                        "Las carreras se actualizarán la próxima vez que abras el buscador",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        OpcionAjuste(
            icono = Icons.Default.Info,
            titulo = "Acerca de",
            valor = "RacPlanner v1.0.0",
            tint = MaterialTheme.colorScheme.primary,
            onClick = {
                mostrarAcercaDe = true
            }
        )
    }
    if (mostrarAcercaDe) {
        AlertDialog(
            onDismissRequest = {
                mostrarAcercaDe = false
            },
            title = {
                Text("Acerca de")
            },
            text = {
                Text(
                    "RacPlanner " +
                            "Versión 1.0.0\n" +
                            "Aplicación para consultar carreras y organizar horarios académicos.\n" +
                            "Desarrollado como proyecto académico."
                )
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarAcercaDe = false
                    }
                ) {
                    Text("Aceptar")
                }
            }
        )
    }
    if (mostrarConfirmacionLimpiar) {
        AlertDialog(
            onDismissRequest = {
                mostrarConfirmacionLimpiar = false
            },
            title = {
                Text("Limpiar caché")
            },
            text = {
                Text("Se eliminarán las carreras almacenadas, los detalles y el horario guardado. ¿Deseas continuar?")
            },
            confirmButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacionLimpiar = false

                        scope.launch {
                            horarioState.vaciarHorario()

                            app.carreraCacheRepository.limpiar()
                            app.detalleCarreraCacheRepository.limpiar()

                            Toast.makeText(
                                context,
                                "Caché eliminada correctamente",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = {
                        mostrarConfirmacionLimpiar = false
                    }
                ) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun OpcionAjuste(
    icono: ImageVector,
    titulo: String,
    valor: String,
    tint: Color = MaterialTheme.colorScheme.primary,
    onClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .clickable{onClick()},
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Icon(
                imageVector = icono,
                contentDescription = null,
                tint = tint
            )

            Spacer(modifier = Modifier.width(12.dp))
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = titulo,
                    style = MaterialTheme.typography.titleMedium
                )
                Text(
                    text = valor,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = null,
                tint = tint
            )
        }
    }
}