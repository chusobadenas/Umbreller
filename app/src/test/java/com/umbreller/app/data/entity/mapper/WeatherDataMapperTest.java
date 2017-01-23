package com.umbreller.app.data.entity.mapper;

import android.text.format.DateUtils;

import com.umbreller.app.data.entity.CityEntity;
import com.umbreller.app.data.entity.RainEntity;
import com.umbreller.app.data.entity.WeatherDetailEntity;
import com.umbreller.app.data.entity.WeatherEntity;
import com.umbreller.app.domain.Weather;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Locale;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DateUtils.class)
public class WeatherDataMapperTest {

  private WeatherDataMapper mWeatherDataMapper;

  @Before
  public void setUp() {
    PowerMockito.mockStatic(DateUtils.class);
    mWeatherDataMapper = new WeatherDataMapper();
  }

  @Test
  public void testTransformWeather() {
    CityEntity city = new CityEntity();
    RainEntity rain = new RainEntity();
    WeatherDetailEntity details = new WeatherDetailEntity();
    WeatherEntity weatherEntity = new WeatherEntity();
    Date date = new Date();
    DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
    String today = dateFormatter.format(date);

    city.setId(1);
    city.setName("Barcelona");
    rain.setVolume(0.23);
    details.setDataForecast(today);
    details.setRain(rain);
    weatherEntity.setCity(city);
    weatherEntity.setDetails(Arrays.asList(details));

    PowerMockito.when(DateUtils.isToday(Mockito.anyLong())).thenReturn(true);

    Weather weather = mWeatherDataMapper.transform(weatherEntity);

    assertNotNull(weather);
    assertEquals(Double.valueOf(weather.getRainVolume()), Double.valueOf(0.23));
    assertEquals(weather.getRainTime(), today);
  }
}
