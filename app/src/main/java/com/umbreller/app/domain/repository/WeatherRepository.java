package com.umbreller.app.domain.repository;

import com.umbreller.app.domain.Weather;

import rx.Observable;

public interface WeatherRepository {

  /**
   * Get an {@link rx.Observable} which will emit an object of {@link Weather}.
   */
  Observable<Weather> getWeatherByCurrentLocation();
}
