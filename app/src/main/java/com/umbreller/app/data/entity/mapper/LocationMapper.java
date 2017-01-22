package com.umbreller.app.data.entity.mapper;

import android.location.Location;

import com.umbreller.app.data.entity.LocationEntity;

import javax.inject.Inject;

public class LocationMapper {

  @Inject
  public LocationMapper() {
    // Empty constructor
  }

  public LocationEntity transform(Location location) {
    LocationEntity locationEntity = null;

    if (location != null) {
      locationEntity = new LocationEntity();
      locationEntity.setLatitude(location.getLatitude());
      locationEntity.setLongitude(location.getLongitude());
    }

    return locationEntity;
  }
}
