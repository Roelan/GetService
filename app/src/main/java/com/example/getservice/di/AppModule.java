package com.example.getservice.di;

import android.content.Context;

import androidx.annotation.NonNull;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class AppModule {

    private Context applicationContext;

    public AppModule(@NonNull Context context) {
        applicationContext = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return applicationContext;
    }
}
