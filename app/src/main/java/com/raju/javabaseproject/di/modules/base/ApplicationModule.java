package com.raju.javabaseproject.di.modules.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.gson.Gson;
import com.raju.javabaseproject.MyApp;
import com.raju.javabaseproject.R;

import dagger.Module;
import dagger.Provides;

@Module
public class ApplicationModule {

    @Provides
    Application providesApplication(MyApp app) {
        return app;
    }

    @Provides
    Context providesContext(MyApp app) {
        return app.getApplicationContext();
    }

    @Provides
    Gson providesGson() {
        return new Gson();
    }

    @Provides
    SharedPreferences providesSharedPreferences(MyApp app) {
        return app.getSharedPreferences(app.getResources().getString(R.string.pref_name), Context.MODE_PRIVATE);
    }
}
