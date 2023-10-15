package com.codezilla.cleanarchitecturenoteapp.di

import android.app.Application
import androidx.room.Room
import com.codezilla.cleanarchitecturenoteapp.feature_note.data.data_source.NotesDatabase
import com.codezilla.cleanarchitecturenoteapp.feature_note.data.repository.NoteRepoImplementation
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.repository.NoteRepository
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.use_case.DeleteNote
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.use_case.GetNotes
import com.codezilla.cleanarchitecturenoteapp.feature_note.domain.use_case.NotesUseCases
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton

    fun provideNoteDatabase(app:Application):NotesDatabase{

        return  Room.databaseBuilder(
            app,
            NotesDatabase::class.java,
            NotesDatabase.DATABASE_NAME
        ).build()
    }
    @Provides
    @Singleton
    fun provideNoteRepository(db:NotesDatabase) : NoteRepository{
        return  NoteRepoImplementation(db.noteDao)
    }

    @Provides
    @Singleton
    fun provideNoteUseCases(repo: NoteRepository): NotesUseCases{
        return NotesUseCases(
            getNotes = GetNotes(repo),
            deleteNote = DeleteNote(repo)
        )
    }
}