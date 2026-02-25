package com.example.registroestudiante.data.local.repository

import com.example.registroestudiante.data.local.dao.AsignaturaDao
import com.example.registroestudiante.data.local.mapper.toDomain
import com.example.registroestudiante.data.local.mapper.toEntity
import com.example.registroestudiante.domain.model.Asignatura
import com.example.registroestudiante.domain.repository.AsignaturaRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class AsignaturaRepositoryImpl @Inject constructor(
    private val dao: AsignaturaDao
) : AsignaturaRepository {

    override fun obtenerTodas(): Flow<List<Asignatura>> {
        return dao.obtenerTodas().map { lista ->
            lista.map { it.toDomain() }
        }
    }

    override suspend fun obtenerPorId(id: Int): Asignatura? {
        return dao.obtenerPorId(id)?.toDomain()
    }

    override suspend fun guardar(asignatura: Asignatura) {
        dao.insertar(asignatura.toEntity())
    }

    override suspend fun eliminar(asignatura: Asignatura) {
        dao.eliminar(asignatura.toEntity())
    }

    override suspend fun existeNombre(nombre: String): Boolean {
        return dao.existeNombre(nombre)
    }
}