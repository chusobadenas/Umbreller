package com.umbreller.app.common.di.components;

import com.umbreller.app.common.di.PerActivity;
import com.umbreller.app.common.di.modules.ActivityModule;
import com.umbreller.app.common.di.modules.LocationModule;
import com.umbreller.app.common.di.modules.WeatherModule;
import com.umbreller.app.presentation.main.MainFragment;

import dagger.Component;

/**
 * A scope {@link PerActivity} component.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class,
    LocationModule.class, WeatherModule.class})
public interface MainComponent extends ActivityComponent {

  void inject(MainFragment mainFragment);
}
