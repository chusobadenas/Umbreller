package com.umbreller.app;

import android.app.Application;

import com.umbreller.app.common.di.HasComponent;
import com.umbreller.app.common.di.components.ApplicationComponent;
import com.umbreller.app.common.di.components.DaggerApplicationComponent;
import com.umbreller.app.common.di.modules.ApplicationModule;

import timber.log.Timber;

public class AndroidApplication extends Application implements HasComponent<ApplicationComponent> {

  private ApplicationComponent mApplicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();
    initializeInjector();
    initializeTimberLogger();
  }

  @Override
  public ApplicationComponent getComponent() {
    return mApplicationComponent;
  }

  private void initializeInjector() {
    mApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this))
        .build();
  }

  private void initializeTimberLogger() {
    if (BuildConfig.DEBUG) {
      Timber.plant(new Timber.DebugTree());
    }
  }
}
