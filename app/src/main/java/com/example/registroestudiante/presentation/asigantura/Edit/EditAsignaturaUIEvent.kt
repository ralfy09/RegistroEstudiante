package com.example.registroestudiante.presentation.asigantura.Edit

sealed class EditAsignaturaUIEvent {
    data class OnCodigoChange(val value: String) : EditAsignaturaUIEvent()
    data class OnNombreChange(val value: String) : EditAsignaturaUIEvent()
    data class OnAulaChange(val value: String) : EditAsignaturaUIEvent()
    data class OnCreditosChange(val value: String) : EditAsignaturaUIEvent()
    object OnGuardar : EditAsignaturaUIEvent()
}