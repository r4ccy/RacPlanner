package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp

@Composable
fun Ajustes(
    cambiarPantalla: (String) -> Unit
) {
    Column(modifier = Modifier
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
        OpcionAjuste(
            icono = Icons.Default.Palette,
            titulo = "Tema",
            valor = "Tema del sistema"
        )
        Spacer(modifier = Modifier.height(12.dp))
        OpcionAjuste(
            icono = Icons.Default.Info,
            titulo = "Acerca de",
            valor = "RacPlanner v1.0.0"
        )
    }
}

@Composable
private fun OpcionAjuste(
    icono: ImageVector,
    titulo: String,
    valor: String
) {
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
                imageVector = icono,
                contentDescription = null
            )
            Spacer(modifier = Modifier.height(12.dp))
            Column(modifier = Modifier.weight(1f)) {
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
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null
            )
        }
    }
}