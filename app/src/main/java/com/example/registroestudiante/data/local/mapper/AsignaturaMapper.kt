package com.example.registroestudiante.data.local.mapper

import com.example.registroestudiante.data.local.entities.AsignaturaEntity
import com.example.registroestudiante.domain.model.Asignatura

fun AsignaturaEntity.toDomain() = Asignatura(
    asignaturaId = asignaturaId,
    codigo = codigo,
    nombre = nombre,
    aula = aula,
    creditos = creditos
)

fun Asignatura.toEntity() = AsignaturaEntity(
    asignaturaId = asignaturaId,
    codigo = codigo,
    nombre = nombre,
    aula = aula,
    creditos = creditos
)