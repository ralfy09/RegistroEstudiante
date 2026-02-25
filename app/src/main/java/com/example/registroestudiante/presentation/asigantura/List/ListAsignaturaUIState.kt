package com.example.registroestudiante.presentation.asigantura.List

import com.example.registroestudiante.domain.model.Asignatura

data class ListAsignaturaUIState(
    val asignaturas: List<Asignatura> = emptyList(),
    val isLoading: Boolean = false,
    val error: String? = null
)