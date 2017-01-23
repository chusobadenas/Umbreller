package com.umbreller.app.common.di.modules;

import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.data.repository.LocationManager;
import com.umbreller.app.domain.interactor.UseCase;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;

public class LocationModuleTest {

  private LocationModule mLocationModule;

  @Mock
  private LocationManager mLocationManager;
  @Mock
  private ThreadExecutor mThreadExecutor;
  @Mock
  private PostExecutionThread mPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mLocationModule = new LocationModule();
  }

  @Test
  public void testProvideGetWeatherSuccess() {
    UseCase getLocation = mLocationModule.provideGetLocation(mLocationManager, mThreadExecutor,
        mPostExecutionThread);
    assertNotNull(getLocation);
  }
}
