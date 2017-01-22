package com.umbreller.app.domain.interactor.location;

import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.domain.interactor.UseCase;
import com.umbreller.app.domain.repository.LocationRepository;

import javax.inject.Inject;

import rx.Observable;

public class GetLocation extends UseCase {

  private final LocationRepository mLocationRepository;

  @Inject
  public GetLocation(LocationRepository locationRepository, ThreadExecutor threadExecutor,
                     PostExecutionThread postExecutionThread) {
    super(threadExecutor, postExecutionThread);
    mLocationRepository = locationRepository;
  }

  @Override
  public Observable buildUseCaseObservable(Object... param) {
    return mLocationRepository.getLocation();
  }
}
