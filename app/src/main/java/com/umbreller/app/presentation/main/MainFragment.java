package com.umbreller.app.presentation.main;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.umbreller.app.R;
import com.umbreller.app.common.di.components.MainComponent;
import com.umbreller.app.presentation.base.BaseFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Main fragment
 */
public class MainFragment extends BaseFragment implements MainMvpView {

  @Inject
  MainPresenter mMainPresenter;

  @BindView(R.id.main_layout)
  LinearLayout mMainLayout;
  @BindView(R.id.rl_progress)
  RelativeLayout mViewProgress;
  @BindView(R.id.text_location)
  TextView mTextLocation;
  @BindView(R.id.text_umbrella)
  TextView mTextUmbrella;
  @BindView(R.id.text_hour)
  TextView mTextHour;

  private Unbinder mUnbinder;

  /**
   * Creates a new instance of a MainFragment.
   */
  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getComponent(MainComponent.class).inject(this);
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    View fragmentView = inflater.inflate(R.layout.fragment_main, container, false);
    mUnbinder = ButterKnife.bind(this, fragmentView);
    mMainPresenter.attachView(this);
    return fragmentView;
  }

  @Override
  public void onStart() {
    super.onStart();
    loadWeather();
  }

  @Override
  public void onDestroyView() {
    super.onDestroyView();
    mUnbinder.unbind();
  }

  @Override
  public void onDestroy() {
    super.onDestroy();
    mMainPresenter.detachView();
  }

  @Override
  public void showLoading() {
    mMainLayout.setVisibility(View.GONE);
    mViewProgress.setVisibility(View.VISIBLE);
  }

  @Override
  public void hideLoading() {
    mMainLayout.setVisibility(View.VISIBLE);
    mViewProgress.setVisibility(View.GONE);
  }

  @Override
  public void showError(String message) {
    showToastMessage(message);
  }

  @Override
  public Context context() {
    return getActivity();
  }

  /**
   * Loads the weather
   */
  private void loadWeather() {
    mMainPresenter.initialize();
  }

  @Override
  public void showCurrentLocation(String location) {
    mTextLocation.setText(location);
  }

  @Override
  public void showUmbrellaStatus(boolean status) {
    if (status) {
      mTextUmbrella.setText(R.string.umbrella_yes);
    } else {
      mTextUmbrella.setText(R.string.umbrella_no);
    }
  }

  @Override
  public void showRainTime(String rainTime) {
    if (rainTime != null) {
      mTextHour.setText(rainTime);
    }
  }
}
