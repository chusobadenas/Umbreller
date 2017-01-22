package com.umbreller.app.data.repository;

import com.umbreller.app.data.entity.LocationEntity;
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
  private final LocationManager mLocationManager;
  private final WeatherDataMapper mWeatherDataMapper;

  @Inject
  public WeatherDataRepository(APIService apiService, LocationManager locationManager,
                               WeatherDataMapper weatherDataMapper) {
    mApiService = apiService;
    mLocationManager = locationManager;
    mWeatherDataMapper = weatherDataMapper;
  }

  private Observable<Weather> getWeather(LocationEntity locationEntity) {
    String latitude = locationEntity.getLatitude().toString();
    String longitude = locationEntity.getLongitude().toString();

    return mApiService.getWeather(latitude, longitude, APIService.API_KEY)
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

  @Override
  public Observable<Weather> getWeatherByCurrentLocation() {
    return mLocationManager.getLocation()
        .flatMap(new Func1<LocationEntity, Observable<Weather>>() {
          @Override
          public Observable<Weather> call(LocationEntity locationEntity) {
            return getWeather(locationEntity);
          }
        });
  }
}
