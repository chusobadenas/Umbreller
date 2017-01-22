package com.umbreller.app.common.exception;

/**
 * Exception throw by the application when a there is a network connection exception.
 */
class NetworkConnectionException extends Exception {

  NetworkConnectionException() {
    super();
  }

  NetworkConnectionException(String message) {
    super(message);
  }

  NetworkConnectionException(String message, Throwable cause) {
    super(message, cause);
  }

  NetworkConnectionException(Throwable cause) {
    super(cause);
  }
}
