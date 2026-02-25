package com.example.registroestudiante.domain.usecase.AsignaturaUseCases

import com.example.registroestudiante.domain.model.Asignatura
import com.example.registroestudiante.domain.repository.AsignaturaRepository

class EliminarAsignaturaUseCase(
    private val repository: AsignaturaRepository
) {
    suspend operator fun invoke(asignatura: Asignatura) {
        repository.eliminar(asignatura)
    }
}