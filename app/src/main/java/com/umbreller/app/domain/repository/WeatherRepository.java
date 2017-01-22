package com.umbreller.app.domain.repository;

import com.umbreller.app.domain.Weather;

import rx.Observable;

public interface WeatherRepository {

  Observable<Weather> getWeather();
}
