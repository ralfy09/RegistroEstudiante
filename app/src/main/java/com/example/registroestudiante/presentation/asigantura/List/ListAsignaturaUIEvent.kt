package com.example.registroestudiante.presentation.asigantura.List

import com.example.registroestudiante.domain.model.Asignatura

sealed interface ListAsignaturaUIEvent {

    data object OnRefresh : ListAsignaturaUIEvent

    data class OnEliminar(
        val asignatura: Asignatura
    ) : ListAsignaturaUIEvent

    data class OnEditar(
        val asignaturaId: Int
    ) : ListAsignaturaUIEvent
}