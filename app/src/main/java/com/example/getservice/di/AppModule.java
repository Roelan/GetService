package com.example.getservice.di;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;

import com.example.getservice.room.NoteRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Application applicationContext;

    public AppModule(@NonNull Application application) {
        applicationContext = application;
    }

    @Provides
    @Singleton
    Application provideApplicationContext() {
        return applicationContext;
    }
}
