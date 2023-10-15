package com.codezilla.cleanarchitecturenoteapp.feature_note.presentation.notes

import androidx.lifecycle.ViewModel
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class  NotesViewModel
@Inject constructor(
    private val notesUseCases: NotesUseCases
):ViewModel(){


}

