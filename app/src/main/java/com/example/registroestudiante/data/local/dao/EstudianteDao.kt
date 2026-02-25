package com.example.registroestudiante.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.registroestudiante.data.local.entities.EstudianteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface EstudianteDao {

    @Query("SELECT * FROM estudiantes ORDER BY nombres")
    fun obtenerTodos(): Flow<List<EstudianteEntity>>

    @Query("SELECT * FROM estudiantes WHERE estudianteId = :id")
    suspend fun obtenerPorId(id: Int): EstudianteEntity?

    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertar(estudiante: EstudianteEntity)

    @Update
    suspend fun actualizar(estudiante: EstudianteEntity)

    @Delete
    suspend fun eliminar(estudiante: EstudianteEntity)

    @Query("""
    SELECT COUNT(*) > 0 
    FROM estudiantes
    WHERE nombres = :nombre 
    AND estudianteId != :estudianteId
""")
    suspend fun existeNombre(
        nombre: String,
        estudianteId: Int
    ): Boolean

}