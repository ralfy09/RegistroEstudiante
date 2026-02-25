package com.example.registroestudiante.presentation.penalidad.Edit

sealed class EditEstudianteUIEvent {
    data class OnNombreChange(val value: String) : EditEstudianteUIEvent()
    data class OnEmailChange(val value: String) : EditEstudianteUIEvent()
    data class OnEdadChange(val value: String) : EditEstudianteUIEvent()
    object OnGuardar : EditEstudianteUIEvent()
}