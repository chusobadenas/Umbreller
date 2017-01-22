package com.umbreller.app.common.util;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import org.easymock.EasyMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.powermock.api.easymock.PowerMock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import static org.junit.Assert.assertEquals;

@RunWith(PowerMockRunner.class)
@PrepareForTest(DialogFactory.class)
public class DialogFactoryTest {

  private static final String TITLE = "Say hi";
  private static final String MESSAGE = "Hello, world!";

  @Mock
  Context mMockContext;
  @Mock
  AlertDialog mMockDialog;
  @Mock
  DialogInterface.OnClickListener mListener;

  @Before
  public void setUp() {
    MockitoAnnotations.initMocks(this);
  }

  @Test
  public void testCreateSimpleErrorDialogSuccess() {
    AlertDialog.Builder builder = PowerMock.createMock(AlertDialog.Builder.class);

    try {
      PowerMock.expectNew(AlertDialog.Builder.class, mMockContext).andReturn(builder);

      EasyMock.expect(builder.setTitle(TITLE)).andReturn(builder);
      EasyMock.expect(builder.setMessage(MESSAGE)).andReturn(builder);
      EasyMock.expect(builder.setNeutralButton(android.R.string.ok, null)).andReturn(builder);
      EasyMock.expect(builder.create()).andReturn(mMockDialog);

      PowerMock.replay(builder, AlertDialog.Builder.class);
      Dialog dialog = DialogFactory.createSimpleErrorDialog(mMockContext, TITLE, MESSAGE);
      PowerMock.verify(builder, AlertDialog.Builder.class);

      assertEquals(dialog, mMockDialog);

    } catch (Exception exception) {
      Assert.fail(exception.getMessage());
    }
  }

  @Test
  public void testCreateSimpleConfirmDialogSuccess() {
    AlertDialog.Builder builder = PowerMock.createMock(AlertDialog.Builder.class);

    try {
      PowerMock.expectNew(AlertDialog.Builder.class, mMockContext).andReturn(builder);

      EasyMock.expect(builder.setTitle(TITLE)).andReturn(builder);
      EasyMock.expect(builder.setMessage(MESSAGE)).andReturn(builder);
      EasyMock.expect(builder.setNegativeButton(android.R.string.cancel, null)).andReturn(builder);
      EasyMock.expect(builder.setPositiveButton(android.R.string.ok, mListener)).andReturn(builder);
      EasyMock.expect(builder.create()).andReturn(mMockDialog);

      PowerMock.replay(builder, AlertDialog.Builder.class);
      Dialog dialog = DialogFactory.createConfirmDialog(mMockContext, TITLE, MESSAGE, mListener);
      PowerMock.verify(builder, AlertDialog.Builder.class);

      assertEquals(dialog, mMockDialog);

    } catch (Exception exception) {
      Assert.fail(exception.getMessage());
    }
  }
}
