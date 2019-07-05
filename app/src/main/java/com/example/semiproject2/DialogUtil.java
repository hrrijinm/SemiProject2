package com.example.semiproject2;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.widget.Toast;

public class DialogUtil {

    public static void showDialog(Context context, String title, String msg,
                           String okMsg, DialogInterface.OnClickListener okListener,
                           String cancelMsg, DialogInterface.OnClickListener cancelListener
                           ) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        builder.setMessage(msg);

        if (okListener != null) {
            builder.setPositiveButton(okMsg, okListener);
        }

        if (cancelListener != null) {
            builder.setNegativeButton(cancelMsg, cancelListener);
        }
        builder.show(); // 표시
    }
}
