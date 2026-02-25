package com.example.registroestudiante.presentation.asignatura.Edit


import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EditAsignaturaScreen(
    asignaturaId: Int? = null,
    viewModel: EditAsignaturaViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(asignaturaId) {
        asignaturaId?.let { id ->
            if (id > 0) viewModel.cargarAsignatura(id)
        }
    }

    EditAsignaturaContent(
        state = state,
        onCodigoChange = { viewModel.onEvent(EditAsignaturaUIEvent.OnCodigoChange(it)) },
        onNombreChange = { viewModel.onEvent(EditAsignaturaUIEvent.OnNombreChange(it)) },
        onAulaChange = { viewModel.onEvent(EditAsignaturaUIEvent.OnAulaChange(it)) },
        onCreditosChange = { viewModel.onEvent(EditAsignaturaUIEvent.OnCreditosChange(it)) },
        onGuardar = { viewModel.onEvent(EditAsignaturaUIEvent.OnGuardar) }
    )
}

@Composable
fun EditAsignaturaContent(
    state: EditAsignaturaUIState,
    onCodigoChange: (String) -> Unit,
    onNombreChange: (String) -> Unit,
    onAulaChange: (String) -> Unit,
    onCreditosChange: (String) -> Unit,
    onGuardar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = if (state.asignaturaId == 0) "Registrar Asignatura" else "Editar Asignatura",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(Modifier.height(16.dp))

        OutlinedTextField(
            value = state.codigo,
            onValueChange = onCodigoChange,
            label = { Text("Código") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.nombre,
            onValueChange = onNombreChange,
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.aula,
            onValueChange = onAulaChange,
            label = { Text("Aula") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(12.dp))

        OutlinedTextField(
            value = state.creditos,
            onValueChange = onCreditosChange,
            label = { Text("Créditos") },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(24.dp))

        Button(
            onClick = onGuardar,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Guardar")
        }

        state.mensaje?.let {
            Spacer(Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.primary)
        }

        state.error?.let {
            Spacer(Modifier.height(16.dp))
            Text(it, color = MaterialTheme.colorScheme.error)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EditAsignaturaScreenPreview() {
    EditAsignaturaContent(
        state = EditAsignaturaUIState(
            asignaturaId = 0,
            codigo = "",
            nombre = "",
            aula = "",
            creditos = ""
        ),
        onCodigoChange = {},
        onNombreChange = {},
        onAulaChange = {},
        onCreditosChange = {},
        onGuardar = {}
    )
}