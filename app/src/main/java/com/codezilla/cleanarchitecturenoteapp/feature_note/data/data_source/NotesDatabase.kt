package com.codezilla.cleanarchitecturenoteapp.feature_note.data.data_source

import androidx.room.Database
import androidx.room.RoomDatabase
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.model.Note

@Database(
    entities = [Note::class],
    version = 1
)

abstract class NotesDatabase: RoomDatabase() {

    abstract  val noteDao:NoteDao
}