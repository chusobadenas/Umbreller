package com.umbreller.app.common.di.modules;

import com.umbreller.app.common.di.PerActivity;
import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.data.repository.LocationManager;
import com.umbreller.app.domain.interactor.UseCase;
import com.umbreller.app.domain.interactor.location.GetLocation;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class LocationModule {

  @Provides
  @PerActivity
  @Named("location")
  UseCase provideGetLocation(LocationManager locationManager, ThreadExecutor
      threadExecutor, PostExecutionThread postExecutionThread) {
    return new GetLocation(locationManager, threadExecutor, postExecutionThread);
  }
}
