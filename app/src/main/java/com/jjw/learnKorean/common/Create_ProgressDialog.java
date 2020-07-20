package com.jjw.learnKorean.common;

import android.app.Dialog;
import android.content.Context;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.ProgressBar;

import com.jjw.learnKorean.R;


public class Create_ProgressDialog extends Dialog {

    public static Create_ProgressDialog show(Context context, CharSequence title,
                                             CharSequence message) {
        return show(context, title, message, false);
    }
//    boolean onTouchEvent (MotionEvent event)
//    boolean onKeyDown(int keyCode, KeyEvent event)
//    boolean onKeyUp(int keyConde, KeyEvent event)
//    boolean onTrackballEvent(MotionEvent event)

//    View.OnTouchListener : boolean onTouch (View v, MotionEvent event)
//    View.OnKeyListener : boolean onKey (View v, int keyCode, keyEvent event)
//    View.OnClickListener : void onClick (View v)
//    View.OnLongClickListener : boolean onLongClick (View v)
//    View.OnFocusChangeListener : void onFocusChange(View v, boolean hasFocus)


    public static Create_ProgressDialog show(Context context, CharSequence title,
                                             CharSequence message, boolean indeterminate) {
        return show(context, title, message, indeterminate, false, null);
    }

    public static Create_ProgressDialog show(Context context, CharSequence title,
                                             CharSequence message, boolean indeterminate, boolean cancelable) {
        return show(context, title, message, indeterminate, cancelable, null);
    }

    public static Create_ProgressDialog show(Context context, CharSequence title,
                                             CharSequence message, boolean indeterminate,
                                             boolean cancelable, OnCancelListener cancelListener) {
        Create_ProgressDialog dialog = new Create_ProgressDialog(context);

        dialog.setTitle(title);
        dialog.setCancelable(cancelable);
        dialog.setOnCancelListener(cancelListener);
            /* The next line will add the ProgressBar to the dialog. */

        dialog.addContentView(new ProgressBar(context), new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
        dialog.show();
        return dialog;
    }

    public Create_ProgressDialog(Context context) {
        super(context, R.style.NewDialog);
    }
}
