package com.example.registroestudiante.domain.usecase

import com.example.registroestudiante.domain.model.Estudiante
import com.example.registroestudiante.domain.repository.EstudianteRepository

class ObtenerEstudiantePorIdUseCase(
    private val repository: EstudianteRepository
) {
    suspend operator fun invoke(id: Int): Estudiante? {
        return repository.obtenerPorId(id)
    }
}