package com.example.registroestudiante.domain.usecase.AsignaturaUseCases

import com.example.registroestudiante.domain.model.Asignatura
import com.example.registroestudiante.domain.repository.AsignaturaRepository

class GuardarAsignaturaUseCase(
    private val repository: AsignaturaRepository
) {
    suspend operator fun invoke(asignatura: Asignatura) {
        repository.guardar(asignatura)
    }
}