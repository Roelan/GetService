package com.example.getservice;

import android.app.Application;

import com.example.getservice.di.AppComponent;
import com.example.getservice.di.AppModule;
import com.example.getservice.di.DaggerAppComponent;
import com.example.getservice.di.RoomModule;

public class App extends Application {

    public AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .roomModule(new RoomModule())
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
