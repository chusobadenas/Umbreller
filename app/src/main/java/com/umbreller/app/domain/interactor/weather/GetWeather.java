package com.umbreller.app.domain.interactor.weather;

import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.domain.interactor.UseCase;
import com.umbreller.app.domain.repository.WeatherRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetWeather extends UseCase {

  private final WeatherRepository mWeatherRepository;

  @Inject
  public GetWeather(WeatherRepository weatherRepository, ThreadExecutor threadExecutor,
                    PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    mWeatherRepository = weatherRepository;
  }

  @Override
  public Observable buildUseCaseObservable(Object... param) {
    return mWeatherRepository.getWeather();
  }
}
