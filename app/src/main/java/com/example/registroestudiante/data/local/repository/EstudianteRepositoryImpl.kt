package com.example.registroestudiante.data.local.repository

import com.example.registroestudiante.data.local.dao.EstudianteDao
import com.example.registroestudiante.data.local.mapper.toDomain
import com.example.registroestudiante.data.local.mapper.toEntity
import com.example.registroestudiante.domain.model.Estudiante
import com.example.registroestudiante.domain.repository.EstudianteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class EstudianteRepositoryImpl @Inject constructor(
    private val dao: EstudianteDao
) : EstudianteRepository {

    override fun obtenerTodos(): Flow<List<Estudiante>> =
        dao.obtenerTodos().map { list ->
            list.map { it.toDomain() }
        }

    override suspend fun obtenerPorId(id: Int): Estudiante? =
        dao.obtenerPorId(id)?.toDomain()

    override suspend fun guardar(estudiante: Estudiante) {
        if (estudiante.estudianteId == 0) {
            dao.insertar(estudiante.toEntity())
        } else {
            dao.actualizar(estudiante.toEntity())
        }
    }

    override suspend fun eliminar(estudiante: Estudiante) {
        dao.eliminar(estudiante.toEntity())
    }

    override suspend fun existeNombre(
        nombre: String,
        estudianteId: Int
    ): Boolean {
        return dao.existeNombre(nombre, estudianteId)
    }

}