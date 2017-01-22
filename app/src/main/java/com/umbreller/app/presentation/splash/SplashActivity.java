package com.umbreller.app.presentation.splash;

import android.os.Bundle;

import com.umbreller.app.presentation.base.BaseActivity;

/**
 * Activity that shows the splash screen
 */
public class SplashActivity extends BaseActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    navigator.navigateToMain(this);
    finish();
  }
}
