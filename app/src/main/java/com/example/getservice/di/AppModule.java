package com.example.getservice.di;

import android.app.Application;

import androidx.annotation.NonNull;

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
