package com.umbreller.app.presentation.main;

import javax.inject.Inject;
import javax.inject.Named;

import com.umbreller.app.common.di.PerActivity;
import com.umbreller.app.common.exception.DefaultErrorBundle;
import com.umbreller.app.common.exception.ErrorMessageFactory;
import com.umbreller.app.domain.Weather;
import com.umbreller.app.domain.interactor.DefaultSubscriber;
import com.umbreller.app.domain.interactor.UseCase;
import com.umbreller.app.presentation.base.BasePresenter;
import com.umbreller.app.presentation.base.Presenter;
import timber.log.Timber;

/**
 * {@link Presenter} that controls communication between views and models of the presentation layer.
 */
@PerActivity
public class MainPresenter extends BasePresenter<MainMvpView> {

  private final UseCase mGetWeatherUseCase;

  @Inject
  public MainPresenter(@Named("weather") UseCase getWeatherUseCase) {
    mGetWeatherUseCase = getWeatherUseCase;
  }

  /**
   * Initializes the presenter by start retrieving the user list.
   */
  void initialize() {
    checkViewAttached();
    loadWeather();
  }

  /**
   * Loads the weather
   */
  private void loadWeather() {
    MainMvpView mvpView = getMvpView();
    mvpView.showLoading();
    getWeather();
  }

  private void getWeather() {
    mGetWeatherUseCase.execute(new WeatherSubscriber());
  }

  private final class WeatherSubscriber extends DefaultSubscriber<Weather> {

    @Override
    public void onCompleted() {
      getMvpView().hideLoading();
    }

    @Override
    public void onError(Throwable throwable) {
      Timber.e(throwable, "There was an error loading the weather");
      MainMvpView mvpView = getMvpView();
      mvpView.hideLoading();
      mvpView.showError(ErrorMessageFactory.create(mvpView.context(), new DefaultErrorBundle
          (throwable).getException()));
    }

    @Override
    public void onNext(Weather weather) {
      MainMvpView mvpView = getMvpView();
      mvpView.hideLoading();

      // NO RAIN
      if (weather == null) {
        mvpView.showUmbrellaStatus(false);
        mvpView.showRainTime(null);
      }
      // RAINING, TAKE UMBRELLA
      else {
        mvpView.showUmbrellaStatus(true);
        mvpView.showRainTime(weather.getRainTime());
      }
    }
  }
}
