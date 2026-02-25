package com.example.registroestudiante.presentation.asigantura.Edit

data class EditAsignaturaUIState(
    val asignaturaId: Int = 0,
    val codigo: String = "",
    val nombre: String = "",
    val aula: String = "",
    val creditos: String = "",
    val mensaje: String? = null,
    val error: String? = null
)