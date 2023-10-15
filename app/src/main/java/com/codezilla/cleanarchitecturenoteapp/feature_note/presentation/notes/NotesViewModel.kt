package com.codezilla.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCases
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.util.NoteOrder
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.util.OrderType
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel
@Inject constructor(
    private val notesUseCases: NotesUseCases
) : ViewModel() {

    private val _state = mutableStateOf(NotesState())
    val state: State<NotesState> = _state

    private var recentlyDeletedNote: Note? = null

    private var getNotesjob: Job? = null

    init {
       getNotes(NoteOrder.Date(OrderType.Descending))
    }


    fun onEvent(event: NotesEvent) {
        when (event) {
            is NotesEvent.Order -> {
                if (state.value.noteOrder::class == event.noteOrder::class &&
                    state.value.noteOrder.orderType == event.noteOrder.orderType
                ) {
                    return
                }


                getNotes(event.noteOrder)
            }

            is NotesEvent.RestoreNote -> {
                viewModelScope.launch {
                    notesUseCases.addNote(recentlyDeletedNote ?: return@launch)
                    recentlyDeletedNote = null
                }

            }

            is NotesEvent.DeleteNote -> {
                viewModelScope.launch {
                    notesUseCases.deleteNote(event.note)
                    recentlyDeletedNote = event.note
                }

            }

            is NotesEvent.ToggleOrderSection -> {
                _state.value = _state.value.copy(
                    isOrderSectionVisible = !state.value.isOrderSectionVisible
                )

            }
        }

    }

    private fun getNotes(noteOrder: NoteOrder) {

        getNotesjob?.cancel()
        getNotesjob =
            notesUseCases.getNotes(noteOrder)
                .onEach { notes ->
                    _state.value = state.value.copy(
                        notes = notes,
                        noteOrder = noteOrder
                    )
                }.launchIn(viewModelScope)

    }

}

