package com.umbreller.app.common.di.components;

import android.content.Context;

import com.umbreller.app.common.di.ApplicationContext;
import com.umbreller.app.common.di.modules.ApplicationModule;
import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.data.repository.LocationManager;
import com.umbreller.app.data.repository.WeatherDataRepository;
import com.umbreller.app.data.repository.remote.APIService;
import com.umbreller.app.presentation.base.BaseActivity;
import com.umbreller.app.presentation.navigation.Navigator;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(BaseActivity baseActivity);

  // Exposed to sub-graphs
  @ApplicationContext
  Context context();

  ThreadExecutor threadExecutor();

  PostExecutionThread postExecutionThread();

  APIService apiService();

  Navigator navigator();

  LocationManager locationManager();

  WeatherDataRepository weatherDataRepository();
}
