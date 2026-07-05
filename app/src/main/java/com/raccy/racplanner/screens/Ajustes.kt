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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raccy.racplanner.viewmodel.AjustesVM

@Composable
fun Ajustes() {

    val viewModel: AjustesVM = viewModel()
    val temaOscuro by viewModel.temaOscuro.collectAsState()
    val context = LocalContext.current

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

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "Personaliza la apariencia de la aplicación y consulta información del proyecto",
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant
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
                    contentDescription = null
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
            onClick = {
                Toast.makeText(
                    context,
                    "coming sooooon",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        OpcionAjuste(
            icono = Icons.Default.Refresh,
            titulo = "Refrescar datos",
            valor = "Volver a descargar información",
            onClick = {
                Toast.makeText(
                    context,
                    "Coming sooooon",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )

        Spacer(modifier = Modifier.height(12.dp))
        OpcionAjuste(
            icono = Icons.Default.Info,
            titulo = "Acerca de",
            valor = "RacPlanner v1.0.0",
            onClick = {
                Toast.makeText(
                    context,
                    "Coming sooooon",
                    Toast.LENGTH_SHORT
                ).show()
            }
        )
    }
}

@Composable
private fun OpcionAjuste(
    icono: ImageVector,
    titulo: String,
    valor: String,
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
                contentDescription = null
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
                contentDescription = null
            )
        }
    }
}