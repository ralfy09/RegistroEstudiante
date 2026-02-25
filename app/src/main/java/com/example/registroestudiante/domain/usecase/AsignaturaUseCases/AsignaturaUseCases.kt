package com.example.registroestudiante.domain.usecase.AsignaturaUseCases

data class AsignaturaUseCases(
    val obtenerTodas: ObtenerAsignaturasUseCase,
    val obtenerPorId: ObtenerAsignaturaPorIdUseCase,
    val guardar: GuardarAsignaturaUseCase,
    val eliminar: EliminarAsignaturaUseCase
)