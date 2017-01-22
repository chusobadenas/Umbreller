package com.umbreller.app.common.di.modules;

import com.umbreller.app.common.di.PerActivity;
import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.data.repository.WeatherDataRepository;
import com.umbreller.app.domain.interactor.UseCase;
import com.umbreller.app.domain.interactor.weather.GetWeather;

import javax.inject.Named;

import dagger.Module;
import dagger.Provides;

@Module
public class WeatherModule {

  @Provides
  @PerActivity
  @Named("weather")
  UseCase provideGetWeather(WeatherDataRepository weatherDataRepository, ThreadExecutor
      threadExecutor, PostExecutionThread postExecutionThread) {
    return new GetWeather(weatherDataRepository, threadExecutor, postExecutionThread);
  }
}
