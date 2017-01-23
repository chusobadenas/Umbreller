package com.umbreller.app.presentation.main;

import com.umbreller.app.domain.interactor.DefaultSubscriber;
import com.umbreller.app.domain.interactor.weather.GetWeather;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;

public class MainPresenterTest {

  private MainPresenter mMainPresenter;

  @Mock
  private GetWeather mMockGetWeather;
  @Mock
  private MainMvpView mMockMainMvpView;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    mMainPresenter = new MainPresenter(mMockGetWeather);
    mMainPresenter.attachView(mMockMainMvpView);
  }

  @Test
  public void testAttachViewSuccess() {
    assertNotNull(mMainPresenter.getMvpView());
  }

  @Test
  public void testDetachViewSuccess() {
    mMainPresenter.detachView();
    assertNull(mMainPresenter.getMvpView());
    verify(mMockGetWeather).unsubscribe();
  }

  @Test
  public void testInitializeSuccess() {
    mMainPresenter.initialize();
    verify(mMockGetWeather).execute(any(DefaultSubscriber.class));
  }
}
