package com.example.import.com.example.registroestudiante.di

import com.example.registroestudiante.domain.repository.AsignaturaRepository
import com.example.registroestudiante.domain.repository.EstudianteRepository
import com.example.registroestudiante.domain.usecase.*
import com.example.registroestudiante.domain.usecase.AsignaturaUseCases.AsignaturaUseCases
import com.example.registroestudiante.domain.usecase.AsignaturaUseCases.EliminarAsignaturaUseCase
import com.example.registroestudiante.domain.usecase.AsignaturaUseCases.GuardarAsignaturaUseCase
import com.example.registroestudiante.domain.usecase.AsignaturaUseCases.ObtenerAsignaturaPorIdUseCase
import com.example.registroestudiante.domain.usecase.AsignaturaUseCases.ObtenerAsignaturasUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideEstudianteUseCases(
        repository: EstudianteRepository
    ): EstudianteUseCases {
        return EstudianteUseCases(repository)
    }
}


@Module
@InstallIn(SingletonComponent::class)
object AsignaturaUseCaseModule {

    @Provides
    @Singleton
    fun provideAsignaturaUseCases(
        repository: AsignaturaRepository
    ): AsignaturaUseCases {
        return AsignaturaUseCases(
            obtenerTodas = ObtenerAsignaturasUseCase(repository),
            obtenerPorId = ObtenerAsignaturaPorIdUseCase(repository),
            guardar = GuardarAsignaturaUseCase(repository),
            eliminar = EliminarAsignaturaUseCase(repository)
        )
    }
}