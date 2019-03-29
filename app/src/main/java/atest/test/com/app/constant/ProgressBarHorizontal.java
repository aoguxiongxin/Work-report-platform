package atest.test.com.app.constant;

import android.app.AlertDialog;
import android.content.Context;
import android.view.View;

import atest.test.com.app.R;


/**
 * 作者 : 赵虔
 * 时间 : 2017/11/15
 * 作用 :
 */

public class ProgressBarHorizontal {

    private static AlertDialog alertDialog;

    public static void horizontalProgressBar(Context context) {
        View view = View.inflate(context, R.layout.dialog_uploading, null);
        alertDialog = new AlertDialog.Builder(context, R.style.NoBackGroundDialog)
                .setView(view)
                .setCancelable(false)
                .show();

    }

    public static void dismessProgressBar() {
        if (null != alertDialog && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    public static boolean progressIsShow() {
        if (alertDialog != null && alertDialog.isShowing()) {
            return true;
        } else {
            return false;
        }
    }
}
