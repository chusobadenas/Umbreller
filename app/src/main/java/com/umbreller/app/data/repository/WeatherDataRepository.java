package com.umbreller.app.data.repository;

import com.umbreller.app.data.entity.WeatherEntity;
import com.umbreller.app.data.entity.mapper.WeatherDataMapper;
import com.umbreller.app.data.repository.remote.APIService;
import com.umbreller.app.domain.Weather;
import com.umbreller.app.domain.repository.WeatherRepository;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

public class WeatherDataRepository implements WeatherRepository {

  private final APIService mApiService;
  private final WeatherDataMapper mWeatherDataMapper;

  @Inject
  public WeatherDataRepository(APIService apiService, WeatherDataMapper weatherDataMapper) {
    mApiService = apiService;
    mWeatherDataMapper = weatherDataMapper;
  }

  @Override
  public Observable<Weather> getWeather() {
    return mApiService.getWeather("41.3851", "2.1734", APIService.API_KEY)
        .map(new Func1<Response<WeatherEntity>, Weather>() {
          @Override
          public Weather call(Response<WeatherEntity> weatherEntityResponse) {
            Weather weather = null;

            if (weatherEntityResponse.isSuccessful()) {
              weather = mWeatherDataMapper.transform(weatherEntityResponse.body());
            }

            return weather;
          }
        });
  }
}
