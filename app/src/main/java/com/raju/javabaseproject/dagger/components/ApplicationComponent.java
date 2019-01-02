package com.raju.javabaseproject.dagger.components;

import com.raju.javabaseproject.MyApp;
import com.raju.javabaseproject.dagger.modules.base.ActivityBindingModule;
import com.raju.javabaseproject.dagger.modules.base.ApplicationModule;
import com.raju.javabaseproject.dagger.modules.base.DatabaseModule;
import com.raju.javabaseproject.dagger.modules.base.FragmentBindingModule;
import com.raju.javabaseproject.dagger.modules.base.ImageModule;
import com.raju.javabaseproject.dagger.modules.base.NetworkModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.support.AndroidSupportInjectionModule;

/**
 * Created by Rajashekhar Vanahalli on 05/04/18.
 */
@Singleton
@Component(modules = {
        AndroidSupportInjectionModule.class,
        ApplicationModule.class,
        ActivityBindingModule.class,
        FragmentBindingModule.class,
        NetworkModule.class,
        ImageModule.class,
        DatabaseModule.class

})
public interface ApplicationComponent {
    @Component.Builder
    interface Builder {

        @BindsInstance
        Builder application(MyApp app);

        ApplicationComponent build();
    }

    void inject(MyApp app);
}
