package com.example.registroestudiante.presentation.penalidad.Edit

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroestudiante.domain.model.Estudiante
import com.example.registroestudiante.domain.usecase.EstudianteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class EditEstudianteViewModel @Inject constructor(
    private val useCases: EstudianteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(EditEstudianteUIState())
    val state: StateFlow<EditEstudianteUIState> = _state.asStateFlow()

    fun onEvent(event: EditEstudianteUIEvent) {
        when (event) {
            is EditEstudianteUIEvent.OnNombreChange ->
                _state.update { it.copy(nombres = event.value) }

            is EditEstudianteUIEvent.OnEmailChange ->
                _state.update { it.copy(email = event.value) }

            is EditEstudianteUIEvent.OnEdadChange ->
                _state.update { it.copy(edad = event.value) }

            EditEstudianteUIEvent.OnGuardar ->
                guardar()
        }
    }

    fun cargarEstudiante(id: Int) {
        viewModelScope.launch {
            useCases.obtenerPorId(id)?.let { estudiante ->
                _state.update {
                    it.copy(
                        estudianteId = estudiante.estudianteId,
                        nombres = estudiante.nombres,
                        email = estudiante.email,
                        edad = estudiante.edad.toString()
                    )
                }
            }
        }
    }

    private fun guardar() {
        viewModelScope.launch {
            val s = _state.value

            if (s.nombres.isBlank() || s.email.isBlank() || s.edad.isBlank()) {
                _state.update { it.copy(mensaje = "Todos los campos son obligatorios") }
                return@launch
            }

            if (useCases.existeNombre(s.nombres, s.estudianteId)) {
                _state.update { it.copy(mensaje = "Ya existe un estudiante con ese nombre") }
                return@launch
            }

            useCases.guardar(
                Estudiante(
                    estudianteId = s.estudianteId,
                    nombres = s.nombres,
                    email = s.email,
                    edad = s.edad.toInt()
                )
            )

            _state.value = EditEstudianteUIState(
                mensaje = "Estudiante guardado correctamente"
            )
        }
    }
}