package com.umbreller.app.presentation.navigation;

import android.content.Context;
import android.content.Intent;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.umbreller.app.presentation.main.MainActivity;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

  @Inject
  public Navigator() {
    //empty
  }

  /**
   * Goes to the main screen.
   *
   * @param context A Context needed to open the destiny activity.
   */
  public void navigateToMain(Context context) {
    if (context != null) {
      Intent intentToLaunch = MainActivity.getCallingIntent(context);
      context.startActivity(intentToLaunch);
    }
  }
}
