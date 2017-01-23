package com.umbreller.app.presentation.main;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.widget.Toolbar;

import com.umbreller.app.R;
import com.umbreller.app.common.di.HasComponent;
import com.umbreller.app.common.di.components.DaggerMainComponent;
import com.umbreller.app.common.di.components.MainComponent;
import com.umbreller.app.presentation.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * Main activity
 */
public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {

  private static final int REQUEST_LOCATION = 1;

  @BindView(R.id.toolbar)
  Toolbar mToolbar;

  private MainComponent mainComponent;

  public static Intent getCallingIntent(Context context) {
    return new Intent(context, MainActivity.class);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    ButterKnife.bind(this);
    initializeInjector();
    setSupportActionBar(mToolbar);
    askForLocationPermission();
    if (savedInstanceState == null) {
      addFragment(R.id.fragmentContainer, MainFragment.newInstance());
    }
  }

  private void initializeInjector() {
    mainComponent = DaggerMainComponent.builder()
        .applicationComponent(getApplicationComponent())
        .activityModule(getActivityModule())
        .build();
  }

  @Override
  public MainComponent getComponent() {
    return mainComponent;
  }

  private void askForLocationPermission() {
    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) !=
        PackageManager.PERMISSION_GRANTED) {
      requestLocationPermission();
    }
  }

  private void requestLocationPermission() {
    Timber.d("Location permission has not been granted. Requesting permission...");
    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
        REQUEST_LOCATION);

  }

  @Override
  public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull
      int[] grantResults) {
    switch (requestCode) {
      case REQUEST_LOCATION:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          Timber.d("Location permission has now been granted.");
        }
        break;
      default:
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        break;
    }
  }
}
