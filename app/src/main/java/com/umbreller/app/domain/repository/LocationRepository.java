package com.umbreller.app.domain.repository;

import com.umbreller.app.data.entity.LocationEntity;

import rx.Observable;

public interface LocationRepository {

  /**
   * Get an {@link rx.Observable} which will emit an object of {@link LocationEntity}.
   */
  Observable<LocationEntity> getLocation();
}
