package com.example.registroestudiante.presentation.penalidad.List

import com.example.registroestudiante.domain.model.Estudiante

data class ListEstudianteUIState(
    val isLoading: Boolean = false,
    val estudiantes: List<Estudiante> = emptyList(),
    val error: String? = null
)