package com.umbreller.app.data.entity.mapper;

import android.location.Location;

import com.umbreller.app.data.entity.LocationEntity;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

public class LocationMapperTest {

  private static final Double LATITUDE = 1.0;
  private static final Double LONGITUDE = 1.0;

  private LocationMapper mLocationMapper;

  @Mock
  private Location mMockLocation;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mLocationMapper = new LocationMapper();
  }

  @Test
  public void testTransformLocation() {
    when(mMockLocation.getLatitude()).thenReturn(LATITUDE);
    when(mMockLocation.getLongitude()).thenReturn(LONGITUDE);

    LocationEntity locationEntity = mLocationMapper.transform(mMockLocation);

    assertNotNull(locationEntity);
    assertEquals(locationEntity.getLatitude(), LATITUDE);
    assertEquals(locationEntity.getLongitude(), LONGITUDE);
  }
}
