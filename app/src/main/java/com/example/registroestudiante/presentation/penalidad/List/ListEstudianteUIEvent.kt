package com.example.registroestudiante.presentation.penalidad.List

import com.example.registroestudiante.domain.model.Estudiante

sealed interface ListEstudianteUIEvent {
    data object OnLoad : ListEstudianteUIEvent
    data class OnEliminar(val estudiante: Estudiante) : ListEstudianteUIEvent
}