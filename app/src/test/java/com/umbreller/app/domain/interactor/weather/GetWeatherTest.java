package com.umbreller.app.domain.interactor.weather;

import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.domain.Weather;
import com.umbreller.app.domain.repository.WeatherRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import rx.Observable;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class GetWeatherTest {

  private GetWeather mGetWeather;

  @Mock
  private WeatherRepository mMockWeatherRepository;
  @Mock
  private ThreadExecutor mMockThreadExecutor;
  @Mock
  private PostExecutionThread mMockPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mGetWeather = new GetWeather(mMockWeatherRepository, mMockThreadExecutor, mMockPostExecutionThread);
  }

  @Test
  public void testGetWeatherSuccess() {
    Weather weather = new Weather();
    Observable<Weather> observable = Observable.just(weather);
    when(mMockWeatherRepository.getWeatherByCurrentLocation()).thenReturn(observable);

    assertNotNull(mGetWeather.buildUseCaseObservable());

    verify(mMockWeatherRepository).getWeatherByCurrentLocation();
    verifyNoMoreInteractions(mMockWeatherRepository);
    verifyZeroInteractions(mMockThreadExecutor);
    verifyZeroInteractions(mMockPostExecutionThread);
  }
}
