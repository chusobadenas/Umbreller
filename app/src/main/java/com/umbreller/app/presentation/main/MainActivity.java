package com.umbreller.app.presentation.main;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.umbreller.app.R;
import com.umbreller.app.common.di.HasComponent;
import com.umbreller.app.common.di.components.DaggerMainComponent;
import com.umbreller.app.common.di.components.MainComponent;
import com.umbreller.app.presentation.base.BaseActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Main activity
 */
public class MainActivity extends BaseActivity implements HasComponent<MainComponent> {

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
}
