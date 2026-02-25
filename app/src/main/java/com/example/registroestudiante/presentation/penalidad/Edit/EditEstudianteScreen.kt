package com.example.registroestudiante.presentation.penalidad.Edit

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditEstudianteScreen(
    estudianteId: Int? = null,
    viewModel: EditEstudianteViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(estudianteId) {
        estudianteId?.let {
            if (it != 0) viewModel.cargarEstudiante(it)
        }
    }

    EditEstudianteContent(
        state = state,
        onEvent = viewModel::onEvent
    )
}

@Composable
fun EditEstudianteContent(
    state: EditEstudianteUIState,
    onEvent: (EditEstudianteUIEvent) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = if (state.estudianteId == 0)
                "Registro de Estudiantes"
            else
                "Editar Estudiante",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(24.dp))

        OutlinedTextField(
            value = state.nombres,
            onValueChange = { onEvent(EditEstudianteUIEvent.OnNombreChange(it)) },
            label = { Text("Nombres") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.email,
            onValueChange = { onEvent(EditEstudianteUIEvent.OnEmailChange(it)) },
            label = { Text("Email") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.edad,
            onValueChange = { onEvent(EditEstudianteUIEvent.OnEdadChange(it)) },
            label = { Text("Edad") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = { onEvent(EditEstudianteUIEvent.OnGuardar) },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        state.mensaje?.let {
            Spacer(Modifier.height(16.dp))
            Text(text = it, color = MaterialTheme.colorScheme.primary)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditEstudianteScreenPreview() {
    MaterialTheme {
        EditEstudianteContent(
            state = EditEstudianteUIState(
                estudianteId = 0,
                nombres = "Juan Pérez",
                email = "juan@gmail.com",
                edad = "21",
                mensaje = null
            ),
            onEvent = {}
        )
    }
}