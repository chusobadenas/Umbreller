package com.umbreller.app.domain.interactor;

import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.schedulers.Schedulers;
import rx.subscriptions.Subscriptions;

/**
 * Abstract class for a Use Case (Interactor in terms of Clean Architecture).
 * This interface represents a execution unit for different use cases (this means any use case
 * in the application should implement this contract).
 * <p>
 * By convention each UseCase implementation will return the result using a {@link rx.Subscriber}
 * that will execute its job in a background thread and will post the result in the UI thread.
 */
public abstract class UseCase {

  private final ThreadExecutor mThreadExecutor;
  private final PostExecutionThread mPostExecutionThread;

  private Subscription mSubscription = Subscriptions.unsubscribed();

  public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
    this.mThreadExecutor = threadExecutor;
    this.mPostExecutionThread = postExecutionThread;
  }

  /**
   * Builds an {@link rx.Observable} which will be used when executing the current {@link UseCase}.
   */
  public abstract Observable buildUseCaseObservable(Object... param);

  /**
   * Executes the current use case.
   *
   * @param useCaseSubscriber The guy who will be listen to the observable build
   *                          with {@link #buildUseCaseObservable(Object...)}.
   */
  @SuppressWarnings("unchecked")
  public void execute(Subscriber useCaseSubscriber, Object... param) {
    this.unsubscribe();
    mSubscription = this.buildUseCaseObservable(param)
        .subscribeOn(Schedulers.from(mThreadExecutor))
        .observeOn(mPostExecutionThread.getScheduler())
        .subscribe(useCaseSubscriber);
  }

  /**
   * Unsubscribes from current {@link rx.Subscription}.
   */
  public void unsubscribe() {
    if (!mSubscription.isUnsubscribed()) {
      mSubscription.unsubscribe();
    }
  }
}
