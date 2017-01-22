package com.umbreller.app.common.exception;

import android.content.Context;

import com.umbreller.app.R;

import java.net.UnknownHostException;

import retrofit2.adapter.rxjava.HttpException;

/**
 * Factory used to create error messages from an Exception as a condition.
 */
public final class ErrorMessageFactory {

  private ErrorMessageFactory() {
    //empty
  }

  /**
   * Creates a String representing an error message.
   *
   * @param context   Context needed to retrieve string resources.
   * @param exception An exception used as a condition to retrieve the correct error message.
   * @return {@link String} an error message.
   */
  public static String create(Context context, Exception exception) {
    String message = context.getString(R.string.exception_message_generic);

    if (exception instanceof NetworkConnectionException || exception instanceof UnknownHostException) {
      message = context.getString(R.string.exception_message_no_connection);
    } else if (exception instanceof HttpException) {
      message = ((HttpException) exception).message();
    }

    return message;
  }
}
