package com.raju.javabaseproject.di.components;

import com.raju.javabaseproject.MyApp;
import com.raju.javabaseproject.di.modules.base.ActivityModule;
import com.raju.javabaseproject.di.modules.base.ApplicationModule;
import com.raju.javabaseproject.di.modules.base.DatabaseModule;
import com.raju.javabaseproject.di.modules.base.FragmentsModule;
import com.raju.javabaseproject.di.modules.base.ImageModule;
import com.raju.javabaseproject.di.modules.base.NetworkModule;
import com.raju.javabaseproject.di.modules.base.ServiceModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityModule.class,
        ServiceModule.class,
        FragmentsModule.class,
        NetworkModule.class,
        ImageModule.class,
        DatabaseModule.class
})
public interface ApplicationComponent {

    @Component.Builder
    interface Builder{
        @BindsInstance
        Builder application(MyApp app);

        ApplicationComponent build();
    }

    void inject(MyApp app);
}