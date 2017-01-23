package com.umbreller.app.data.entity.mapper;

import android.text.format.DateUtils;

import com.umbreller.app.data.entity.RainEntity;
import com.umbreller.app.data.entity.WeatherDetailEntity;
import com.umbreller.app.data.entity.WeatherEntity;
import com.umbreller.app.domain.Weather;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.inject.Inject;

import timber.log.Timber;

public class WeatherDataMapper {

  @Inject
  public WeatherDataMapper() {
    // Empty constructor
  }

  public Weather transform(WeatherEntity weatherEntity) {
    Weather weather = null;
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());

    if (weatherEntity != null) {
      List<WeatherDetailEntity> details = weatherEntity.getDetails();

      for (WeatherDetailEntity detail : details) {
        try {
          Date forecastDate = dateFormatter.parse(detail.getDataForecast());
          if (DateUtils.isToday(forecastDate.getTime())) {
            RainEntity rain = detail.getRain();
            if (rain != null) {
              double rainVolume = rain.getVolume();
              weather = new Weather();
              weather.setRainVolume(rainVolume);
              weather.setRainTime(dateFormatter.format(forecastDate));
              break;
            }
          }
        } catch (ParseException exception) {
          Timber.e(exception, "Date parse error");
        }
      }
    }

    return weather;
  }
}
