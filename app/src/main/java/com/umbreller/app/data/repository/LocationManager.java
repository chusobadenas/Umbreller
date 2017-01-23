package com.umbreller.app.data.repository;

import android.content.Context;
import android.location.Location;

import com.umbreller.app.common.di.ApplicationContext;
import com.umbreller.app.data.entity.LocationEntity;
import com.umbreller.app.data.entity.mapper.LocationMapper;
import com.umbreller.app.domain.repository.LocationRepository;

import javax.inject.Inject;

import pl.charmas.android.reactivelocation.ReactiveLocationProvider;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

public class LocationManager implements LocationRepository {

  private final Context mContext;
  private final LocationMapper mLocationMapper;

  @Inject
  public LocationManager(@ApplicationContext Context context, LocationMapper locationMapper) {
    mContext = context;
    mLocationMapper = locationMapper;
  }

  @Override
  public Observable<LocationEntity> getLocation() {
    ReactiveLocationProvider locationProvider = new ReactiveLocationProvider(mContext);
    return locationProvider.getLastKnownLocation()
        .map(new Func1<Location, LocationEntity>() {
          @Override
          public LocationEntity call(Location location) {
            return mLocationMapper.transform(location);
          }
        })
        .observeOn(Schedulers.io());
  }
}
