package com.example.getservice.di;

import android.app.Application;

import com.example.getservice.room.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RoomModule {

    @Provides
    @Singleton
    NoteRepository provideNoteRepository(Application application) {
        return new NoteRepository(application);
    }
}
