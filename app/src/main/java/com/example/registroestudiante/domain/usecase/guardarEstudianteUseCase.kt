package com.example.registroestudiante.domain.usecase

import com.example.registroestudiante.domain.model.Estudiante
import com.example.registroestudiante.domain.repository.EstudianteRepository

class GuardarEstudianteUseCase(
    private val repository: EstudianteRepository
) {
    suspend operator fun invoke(estudiante: Estudiante) {
        repository.guardar(estudiante)
    }
}