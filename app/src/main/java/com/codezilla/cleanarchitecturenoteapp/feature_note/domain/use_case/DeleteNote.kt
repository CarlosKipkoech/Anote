package com.codezilla.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository

class DeleteNote (private  val noteRepository: NoteRepository) {
    suspend operator  fun invoke(note:Note){
        noteRepository.deleteNote(note)
    }
}