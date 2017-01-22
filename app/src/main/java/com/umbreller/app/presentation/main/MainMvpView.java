package com.umbreller.app.presentation.main;

import com.umbreller.app.presentation.base.MvpView;

/**
 * Interface representing a View in a model view presenter (MVP) pattern.
 */
interface MainMvpView extends MvpView {

  /**
   * Shows the location in the view.
   *
   * @param location The location that will be shown.
   */
  void showCurrentLocation(String location);

  /**
   * Says if you need to take the umbrella or not
   *
   * @param status true if you will need the umbrella, false otherwise
   */
  void showUmbrellaStatus(boolean status);

  /**
   * Shows the rain time in the view.
   *
   * @param rainTime The time when rain will start.
   */
  void showRainTime(String rainTime);
}
