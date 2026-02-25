package com.example.registroestudiante.presentation.penalidad.List

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import kotlinx.coroutines.launch
import com.example.registroestudiante.domain.model.Estudiante

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListEstudianteScreen(
    onEditar: (Int) -> Unit,
    onAgregar: () -> Unit,
    onIrAsignaturas: () -> Unit,
    viewModel: ListEstudianteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()

    var estudianteAEliminar by remember { mutableStateOf<Estudiante?>(null) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Lista de Estudiantes") },
                actions = {
                    IconButton(onClick = onIrAsignaturas) {
                        Icon(Icons.Default.List, contentDescription = "Ir a Asignaturas")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = onAgregar) {
                Icon(Icons.Default.Add, contentDescription = "Agregar estudiante")
            }
        },
        snackbarHost = { SnackbarHost(snackbarHostState) }
    ) { padding ->

        when {
            state.isLoading -> {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator()
                }
            }

            state.error != null -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text(text = state.error!!)
                }
            }

            else -> {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(padding)
                        .padding(16.dp)
                ) {
                    items(state.estudiantes) { estudiante ->
                        EstudianteItem(
                            estudiante = estudiante,
                            onEditar = { onEditar(estudiante.estudianteId) },
                            onEliminar = { estudianteAEliminar = estudiante }
                        )
                    }
                }
            }
        }
    }

    estudianteAEliminar?.let { estudiante ->
        AlertDialog(
            onDismissRequest = { estudianteAEliminar = null },
            title = { Text("Eliminar estudiante") },
            text = { Text("¿Deseas eliminar al estudiante '${estudiante.nombres}'?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.onEvent(ListEstudianteUIEvent.OnEliminar(estudiante))
                        estudianteAEliminar = null
                        scope.launch {
                            snackbarHostState.showSnackbar("Estudiante eliminado")
                        }
                    }
                ) {
                    Text("Eliminar")
                }
            },
            dismissButton = {
                TextButton(onClick = { estudianteAEliminar = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
fun EstudianteItem(
    estudiante: Estudiante,
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
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column {
                Text(text = estudiante.nombres, style = MaterialTheme.typography.titleMedium)
                Text(text = "Email: ${estudiante.email}")
                Text(text = "Edad: ${estudiante.edad}")
            }

            Row {
                IconButton(onClick = onEditar) {
                    Icon(Icons.Default.Edit, contentDescription = "Editar")
                }
                IconButton(onClick = onEliminar) {
                    Icon(Icons.Default.Delete, contentDescription = "Eliminar")
                }
            }
        }
    }
}