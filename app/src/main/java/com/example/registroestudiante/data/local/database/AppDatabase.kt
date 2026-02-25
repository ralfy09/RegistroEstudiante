package com.example.registroestudiante.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.registroestudiante.data.local.dao.AsignaturaDao
import com.example.registroestudiante.data.local.dao.EstudianteDao
import com.example.registroestudiante.data.local.entities.AsignaturaEntity
import com.example.registroestudiante.data.local.entities.EstudianteEntity

@Database(
    entities = [
        EstudianteEntity::class,
        AsignaturaEntity::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun estudianteDao(): EstudianteDao

    abstract fun asignaturaDao(): AsignaturaDao
}
