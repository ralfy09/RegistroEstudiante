package com.example.registroestudiante.presentation.penalidad.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroestudiante.domain.usecase.EstudianteUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListEstudianteViewModel @Inject constructor(
    private val useCases: EstudianteUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ListEstudianteUIState())
    val state: StateFlow<ListEstudianteUIState> = _state

    init {
        cargarEstudiantes()
    }

    private fun cargarEstudiantes() {
        viewModelScope.launch {
            try {
                val lista = useCases.obtenerTodos().first()
                _state.update { it.copy(estudiantes = lista, isLoading = false) }
            } catch (e: Exception) {
                _state.update { it.copy(error = e.message, isLoading = false) }
            }
        }
    }


    fun onEvent(event: ListEstudianteUIEvent) {
        when (event) {
            is ListEstudianteUIEvent.OnLoad -> cargarEstudiantes()
            is ListEstudianteUIEvent.OnEliminar -> {
                viewModelScope.launch {
                    useCases.eliminar(event.estudiante)
                    cargarEstudiantes()
                }
            }
        }
    }
}