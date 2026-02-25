package com.example.registroestudiante.domain.usecase

import com.example.registroestudiante.domain.model.Estudiante
import com.example.registroestudiante.domain.repository.EstudianteRepository
import kotlinx.coroutines.flow.Flow

class EstudianteUseCases(
    private val repository: EstudianteRepository
) {
    fun obtenerTodos(): Flow<List<Estudiante>> {
        return repository.obtenerTodos()
    }

    suspend fun eliminar(estudiante: Estudiante) {
        repository.eliminar(estudiante)
    }

    suspend fun guardar(estudiante: Estudiante) {
        repository.guardar(estudiante)
    }

    suspend fun obtenerPorId(id: Int): Estudiante? {
        return repository.obtenerPorId(id)
    }

    suspend fun existeNombre(nombres: String, estudianteId: Int = 0): Boolean {
        return repository.existeNombre(nombres, estudianteId)
    }

}