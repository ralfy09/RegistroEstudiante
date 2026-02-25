package com.example.registroestudiante.data.local.entities

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "asignaturas",
    indices = [Index(value = ["nombre"], unique = true)]
)
data class AsignaturaEntity(
    @PrimaryKey(autoGenerate = true)
    val asignaturaId: Int = 0,
    val codigo: String,
    val nombre: String,
    val aula: String,
    val creditos: Int
)