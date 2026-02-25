package com.example.registroestudiante.presentation.penalidad.Edit

data class EditEstudianteUIState(
    val estudianteId: Int = 0,
    val nombres: String = "",
    val email: String = "",
    val edad: String = "",
    val mensaje: String? = null
)