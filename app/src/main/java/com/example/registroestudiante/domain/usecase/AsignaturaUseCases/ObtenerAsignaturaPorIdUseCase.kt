package com.example.registroestudiante.domain.usecase.AsignaturaUseCases

import com.example.registroestudiante.domain.model.Asignatura
import com.example.registroestudiante.domain.repository.AsignaturaRepository

class ObtenerAsignaturaPorIdUseCase(
    private val repository: AsignaturaRepository
) {
    suspend operator fun invoke(id: Int): Asignatura? {
        return repository.obtenerPorId(id)
    }
}