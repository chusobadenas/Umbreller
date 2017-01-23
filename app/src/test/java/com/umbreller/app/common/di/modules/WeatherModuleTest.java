package com.umbreller.app.common.di.modules;

import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.data.repository.WeatherDataRepository;
import com.umbreller.app.domain.interactor.UseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class WeatherModuleTest {

  private WeatherModule mWeatherModule;

  @Mock
  private WeatherDataRepository mWeatherDataRepository;
  @Mock
  private ThreadExecutor mThreadExecutor;
  @Mock
  private PostExecutionThread mPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mWeatherModule = new WeatherModule();
  }

  @Test
  public void testProvideGetWeatherSuccess() {
    UseCase getWeather = mWeatherModule.provideGetWeather(mWeatherDataRepository,
        mThreadExecutor, mPostExecutionThread);
    assertNotNull(getWeather);
  }
}
