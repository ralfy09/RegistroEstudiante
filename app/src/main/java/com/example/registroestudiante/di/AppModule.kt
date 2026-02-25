package com.example.registroestudiante.di

import android.content.Context
import androidx.room.Room
import com.example.registroestudiante.data.local.dao.AsignaturaDao
import com.example.registroestudiante.data.local.dao.EstudianteDao
import com.example.registroestudiante.data.local.database
import com.example.registroestudiante.data.local.database.AppDatabase
import com.example.registroestudiante.data.local.repository.AsignaturaRepositoryImpl
import com.example.registroestudiante.data.local.repository.EstudianteRepositoryImpl
import com.example.registroestudiante.data.repository.AsignaturaRepositoryImpl
import com.example.registroestudiante.data.repository.EstudianteRepositoryImpl
import com.example.registroestudiante.domain.repository.AsignaturaRepository
import com.example.registroestudiante.domain.repository.EstudianteRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "registro_estudiantes.db"
        )
            .fallbackToDestructiveMigration()
            .build()


    @Provides
    fun provideEstudianteDao(
        db: AppDatabase
    ): EstudianteDao = db.estudianteDao()

    @Provides
    fun provideAsignaturaDao(
        db: AppDatabase
    ): AsignaturaDao = db.asignaturaDao()


    @Provides
    @Singleton
    fun provideEstudianteRepository(
        dao: EstudianteDao
    ): EstudianteRepository =
        EstudianteRepositoryImpl(dao)

    @Provides
    @Singleton
    fun provideAsignaturaRepository(
        dao: AsignaturaDao
    ): AsignaturaRepository =
        AsignaturaRepositoryImpl(dao)
}