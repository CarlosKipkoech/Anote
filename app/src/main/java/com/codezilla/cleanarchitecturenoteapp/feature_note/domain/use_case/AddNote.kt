package com.codezilla.cleanarchitecturenoteapp.feature_note.domain.use_case

import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.model.Note
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.model.invalidNoteException
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import kotlin.jvm.Throws

class AddNote(private val repository: NoteRepository) {
    @Throws(invalidNoteException::class)
    suspend operator fun invoke(note: Note) {
        if (note.title.isBlank()) {

            throw invalidNoteException("Cannot be blank")
        }
        if (note.content.isBlank()) {

            throw invalidNoteException("Cannot be blank")
        }

        repository.insertNote(note)
    }

}