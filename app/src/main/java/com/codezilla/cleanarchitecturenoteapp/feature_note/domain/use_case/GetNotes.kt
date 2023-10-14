package com.codezilla.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class GetNotes (private val noteRepository: NoteRepository) {

    operator fun  invoke(): Flow<List<Note>> {

    }
}