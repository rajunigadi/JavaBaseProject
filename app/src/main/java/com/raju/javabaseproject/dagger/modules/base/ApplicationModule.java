package com.raju.javabaseproject.dagger.modules.base;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.gson.Gson;
import com.raju.javabaseproject.MyApp;
import com.raju.javabaseproject.R;
import com.raju.javabaseproject.dagger.DatabaseInfo;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */

@Module
public class ApplicationModule {

    @Provides
    Context provideContext(MyApp application) {
        return application.getApplicationContext();
    }

    @Provides
    Application provideApplication(MyApp application) {
        return application;
    }

    @Provides
    @DatabaseInfo
    String provideDatabaseName(MyApp application) {
        return application.getResources().getString(R.string.db_name);
    }

    @Provides
    Gson provideGson() {
        return new Gson();
    }

    @Provides
    @DatabaseInfo
    Integer provideDatabaseVersion(MyApp application) {
        return application.getResources().getInteger(R.integer.db_version);
    }

    @Provides
    SharedPreferences provideSharedPrefs(MyApp application) {
        return application.getSharedPreferences(application.getResources().getString(R.string.db_name), Context.MODE_PRIVATE);
    }

    @Provides
    FirebaseAnalytics providesFirebaseAnalytics(MyApp application) {
        return FirebaseAnalytics.getInstance(application);
    }
}
