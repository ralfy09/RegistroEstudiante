package com.example.registroestudiante.presentation.asigantura.List

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.registroestudiante.domain.model.Asignatura
import com.example.registroestudiante.domain.usecase.AsignaturaUseCases.AsignaturaUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListAsignaturaViewModel @Inject constructor(
    private val useCases: AsignaturaUseCases
) : ViewModel() {

    private val _state = MutableStateFlow(ListAsignaturaUIState())
    val state: StateFlow<ListAsignaturaUIState> = _state.asStateFlow()

    init {
        cargar()
    }

    fun onEvent(event: ListAsignaturaUIEvent) {
        when (event) {
            is ListAsignaturaUIEvent.OnRefresh -> cargar()

            is ListAsignaturaUIEvent.OnEliminar -> eliminar(event.asignatura)

            is ListAsignaturaUIEvent.OnEditar -> {
            }
        }
    }

    private fun cargar() {
        viewModelScope.launch {
            useCases.obtenerTodas()
                .onStart {
                    _state.update {
                        it.copy(isLoading = true, error = null)
                    }
                }
                .catch {
                    _state.update {
                        it.copy(
                            isLoading = false,
                            error = "Error cargando asignaturas"
                        )
                    }
                }
                .collect { lista ->
                    _state.update {
                        it.copy(
                            asignaturas = lista,
                            isLoading = false
                        )
                    }
                }
        }
    }

    private fun eliminar(asignatura: Asignatura) {
        viewModelScope.launch {
            try {
                useCases.eliminar(asignatura)
            } catch (e: Exception) {
                _state.update {
                    it.copy(error = "Error eliminando asignatura")
                }
            }
        }
    }
}