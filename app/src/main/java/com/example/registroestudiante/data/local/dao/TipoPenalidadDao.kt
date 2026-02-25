package com.example.registroestudiante.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.registroestudiante.data.local.entities.TipoPenalidadEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TipoPenalidadDao {

    @Query("SELECT * FROM TiposPenalidades")
    fun getAll(): Flow<List<TipoPenalidadEntity>>

    @Query("SELECT * FROM TiposPenalidades WHERE TipoId = :id")
    suspend fun getById(id: Int): TipoPenalidadEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(entity: TipoPenalidadEntity)

    @Query("DELETE FROM TiposPenalidades WHERE TipoId = :id")
    suspend fun deleteById(id: Int)

    @Query("SELECT COUNT(*) FROM TiposPenalidades WHERE Nombre = :nombre")
    suspend fun exists(nombre: String): Int
}