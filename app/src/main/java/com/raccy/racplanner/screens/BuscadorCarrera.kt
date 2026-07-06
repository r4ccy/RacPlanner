package com.raccy.racplanner.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.lifecycle.viewmodel.compose.viewModel
import com.raccy.racplanner.viewmodel.BuscadorVM
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.raccy.racplanner.RacPlannerApp
import com.raccy.racplanner.repository.CarreraRepository
import com.raccy.racplanner.viewmodel.BuscadorVMFactory

@Composable
fun BuscadorCarrera(
    cambiarPantalla: (String) -> Unit,
    seleccionarCarrera: (String) -> Unit
) {

    val context = LocalContext.current
    val app = context.applicationContext as RacPlannerApp
    val factory = remember {
        BuscadorVMFactory(
            CarreraRepository(
                app.carreraCacheRepository
            )
        )
    }
    val viewModel: BuscadorVM = viewModel(
        factory = factory
    )
    val state by viewModel.state.collectAsState()

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
            text = "Carreras",
            style = MaterialTheme.typography.headlineLarge
        )
        Text(
            text = "${state.carreras.size} resultados",
            style = MaterialTheme.typography.bodyMedium
        )

        when {
            state.isLoading -> {Text("Cargando carreras...")}
            state.error != null -> { state.error?.let { Text(it) }}
            state.carreras.isEmpty() -> {Text("No hay carreras disponibles")}
            else -> {
                LazyColumn (
                    modifier = Modifier.weight(1f),
                    contentPadding = PaddingValues(bottom = 12.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(state.carreras) { carrera ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            elevation = CardDefaults.cardElevation(
                                defaultElevation = 4.dp
                            )
                        ) {
                            Column( modifier = Modifier.padding(16.dp)) {
                                Text(
                                    text = carrera.nombre,
                                    style = MaterialTheme.typography.titleMedium
                                )
                                Spacer(modifier = Modifier.height(2.dp))
                                Text(
                                    text = "Código: ${carrera.codigo}",
                                    style = MaterialTheme.typography.bodySmall,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.End
                                ) {
                                    Button(
                                        onClick = {
                                            seleccionarCarrera(carrera.codigo)
                                            cambiarPantalla("detalle")
                                        }
                                    ) { Text("Ver detalles") }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}