package com.example.registroestudiante.domain.usecase

import com.example.registroestudiante.domain.model.Estudiante
import com.example.registroestudiante.domain.repository.EstudianteRepository
import kotlinx.coroutines.flow.Flow

class ObtenerEstudiantesUseCase(
    private val repository: EstudianteRepository
) {
    operator fun invoke(): Flow<List<Estudiante>> {
        return repository.obtenerTodos()
    }
}