package com.example.getservice.di;

import com.example.getservice.ui.NoteViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, RoomModule.class})
public interface AppComponent {

    void inject(NoteViewModel noteViewModel);
}
