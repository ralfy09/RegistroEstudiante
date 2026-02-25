package com.example.registroestudiante.presentation.asigantura.List

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.registroestudiante.domain.model.Asignatura
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListAsignaturaScreen(
    onEditar: (Int) -> Unit,
    onAgregar: () -> Unit,
    viewModel: ListAsignaturaViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var asignaturaAEliminar by remember { mutableStateOf<Asignatura?>(null) }

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(
                onClick = { onEditar(0) } // FAB para agregar nueva asignatura
            ) {
                Icon(Icons.Default.Add, contentDescription = "Agregar Asignatura")
            }
        },
        snackbarHost = { SnackbarHost(hostState = snackbarHostState) }
    ) { padding ->

        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            when {
                state.isLoading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }

                state.error != null -> {
                    Text(
                        text = state.error!!,
                        modifier = Modifier.align(Alignment.Center),
                        color = MaterialTheme.colorScheme.error
                    )
                }

                else -> {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp)
                    ) {

                        Text(
                            text = "Lista de Asignaturas",
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier.fillMaxWidth(),
                            textAlign = TextAlign.Center
                        )

                        Spacer(Modifier.height(16.dp))

                        LazyColumn {
                            items(state.asignaturas) { asignatura ->
                                AsignaturaItem(
                                    asignatura = asignatura,
                                    onEditar = { onEditar(asignatura.asignaturaId) },
                                    onEliminar = { asignaturaAEliminar = asignatura }
                                )
                            }
                        }
                    }
                }
            }

            asignaturaAEliminar?.let { asignatura ->
                AlertDialog(
                    onDismissRequest = { asignaturaAEliminar = null },
                    title = { Text("Eliminar asignatura") },
                    text = { Text("¿Deseas eliminar la asignatura '${asignatura.nombre}'?") },
                    confirmButton = {
                        TextButton(
                            onClick = {
                                viewModel.onEvent(ListAsignaturaUIEvent.OnEliminar(asignatura))
                                asignaturaAEliminar = null
                                scope.launch {
                                    snackbarHostState.showSnackbar("Asignatura eliminada")
                                }
                            }
                        ) {
                            Text("Eliminar")
                        }
                    },
                    dismissButton = {
                        TextButton(onClick = { asignaturaAEliminar = null }) {
                            Text("Cancelar")
                        }
                    }
                )
            }
        }
    }
}

@Composable
fun AsignaturaItem(
    asignatura: Asignatura,
    onEditar: () -> Unit,
    onEliminar: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {

            Column {
                Text(
                    text = asignatura.nombre,
                    style = MaterialTheme.typography.titleMedium
                )
                Text("Código: ${asignatura.codigo}")
                Text("Aula: ${asignatura.aula}")
                Text("Créditos: ${asignatura.creditos}")
            }

            Row {
                IconButton(onClick = onEditar) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar ${asignatura.nombre}")
                }
                IconButton(onClick = onEliminar) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar ${asignatura.nombre}")
                }
            }
        }
    }
}