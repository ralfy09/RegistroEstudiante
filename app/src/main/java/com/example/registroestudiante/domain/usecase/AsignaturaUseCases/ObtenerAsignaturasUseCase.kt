package com.example.registroestudiante.domain.usecase.AsignaturaUseCases

import com.example.registroestudiante.domain.model.Asignatura
import com.example.registroestudiante.domain.repository.AsignaturaRepository
import kotlinx.coroutines.flow.Flow

class ObtenerAsignaturasUseCase(
    private val repository: AsignaturaRepository
) {
    operator fun invoke(): Flow<List<Asignatura>> {
        return repository.obtenerTodas()
    }
}