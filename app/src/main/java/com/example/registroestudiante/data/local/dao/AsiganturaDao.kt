package com.example.registroestudiante.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registroestudiante.data.local.entities.AsignaturaEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AsignaturaDao {

    @Query("SELECT * FROM asignaturas")
    fun obtenerTodas(): Flow<List<AsignaturaEntity>>

    @Query("SELECT * FROM asignaturas WHERE asignaturaId = :id")
    suspend fun obtenerPorId(id: Int): AsignaturaEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertar(asignatura: AsignaturaEntity)

    @Delete
    suspend fun eliminar(asignatura: AsignaturaEntity)

    @Query("SELECT EXISTS(SELECT 1 FROM asignaturas WHERE nombre = :nombre)")
    suspend fun existeNombre(nombre: String): Boolean
}