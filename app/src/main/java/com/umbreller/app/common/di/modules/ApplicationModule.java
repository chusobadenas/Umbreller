package com.umbreller.app.common.di.modules;

import android.content.Context;

import com.umbreller.app.AndroidApplication;
import com.umbreller.app.common.executor.JobExecutor;
import com.umbreller.app.common.executor.PostExecutionThread;
import com.umbreller.app.common.executor.ThreadExecutor;
import com.umbreller.app.common.executor.UIThread;
import com.umbreller.app.data.repository.remote.APIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {

  private final AndroidApplication mApplication;

  /**
   * Constructor
   *
   * @param application the application
   */
  public ApplicationModule(AndroidApplication application) {
    this.mApplication = application;
  }

  @Provides
  @Singleton
  Context provideApplicationContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
    return jobExecutor;
  }

  @Provides
  @Singleton
  PostExecutionThread providePostExecutionThread(UIThread uiThread) {
    return uiThread;
  }

  @Provides
  @Singleton
  APIService provideApiService() {
    return APIService.Creator.newAPIService();
  }
}
