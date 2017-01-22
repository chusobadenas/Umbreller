package com.umbreller.app.common.exception;

/**
 * Wrapper around Exceptions used to manage default errors.
 */
public class DefaultErrorBundle implements ErrorBundle {

  private static final String DEFAULT_ERROR_MSG = "Unknown error";

  private Exception mException;

  /**
   * Constructor
   *
   * @param throwable the exception
   */
  public DefaultErrorBundle(Throwable throwable) {
    if (throwable instanceof Exception) {
      this.mException = (Exception) throwable;
    }
  }

  @Override
  public Exception getException() {
    return mException;
  }

  @Override
  public String getErrorMessage() {
    return mException == null ? DEFAULT_ERROR_MSG : mException.getMessage();
  }
}
