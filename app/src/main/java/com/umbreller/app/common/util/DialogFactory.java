package com.umbreller.app.common.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

/**
 * Class with static methods for creating dialogs
 */
public final class DialogFactory {

  /**
   * Empty constructor
   */
  private DialogFactory() {
  }

  /**
   * Creates a simple error dialog
   *
   * @param context the context
   * @param title   the title
   * @param message the message
   * @return the dialog
   */
  public static Dialog createSimpleErrorDialog(Context context, String title, String message) {
    AlertDialog.Builder alertDialog = new AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNeutralButton(android.R.string.ok, null);
    return alertDialog.create();
  }

  /**
   * Creates a simple confirm dialog
   *
   * @param context application context
   * @param title   the title to display
   * @param message the message to display
   * @param action  the action to perform
   * @return the dialog
   */
  public static Dialog createConfirmDialog(Context context, String title, String message, DialogInterface.OnClickListener
      action) {
    // Create dialog
    AlertDialog.Builder builder = new AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(message)
        .setNegativeButton(android.R.string.cancel, null)
        .setPositiveButton(android.R.string.ok, action);

    // Return the dialog
    return builder.create();
  }
}
