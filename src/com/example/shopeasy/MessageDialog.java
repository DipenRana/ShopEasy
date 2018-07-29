package com.example.shopeasy;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.Gravity;
import android.widget.TextView;


public class MessageDialog {
    AlertDialog alertDialog;

    public MessageDialog(Context context, String title, String msg) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title);
        TextView textView = new TextView(context);
        textView.setTextSize(20);
        textView.setText(msg);
        textView.setGravity(Gravity.CENTER);
        builder.setView(textView);
        builder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                alertDialog.dismiss();
            }
        });
        alertDialog = builder.create();
    }
}