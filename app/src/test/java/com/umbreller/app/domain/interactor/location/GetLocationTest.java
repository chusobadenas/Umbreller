package com.umbreller.app.domain.interactor.location;

import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.data.entity.LocationEntity;
import com.umbreller.app.domain.repository.LocationRepository;

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

public class GetLocationTest {

  private GetLocation mGetLocation;

  @Mock
  private LocationRepository mLocationRepository;
  @Mock
  private ThreadExecutor mMockThreadExecutor;
  @Mock
  private PostExecutionThread mMockPostExecutionThread;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mGetLocation = new GetLocation(mLocationRepository, mMockThreadExecutor, mMockPostExecutionThread);
  }

  @Test
  public void testGetWeatherSuccess() {
    LocationEntity locationEntity = new LocationEntity();
    Observable<LocationEntity> observable = Observable.just(locationEntity);
    when(mLocationRepository.getLocation()).thenReturn(observable);

    assertNotNull(mGetLocation.buildUseCaseObservable());

    verify(mLocationRepository).getLocation();
    verifyNoMoreInteractions(mLocationRepository);
    verifyZeroInteractions(mMockThreadExecutor);
    verifyZeroInteractions(mMockPostExecutionThread);
  }
}
