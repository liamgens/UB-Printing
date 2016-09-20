package com.liamgensel.ubprinting;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;

/**
 * Created by liamgensel on 11/14/15.
 */
public class PrintButtonListener implements View.OnClickListener {

    private Context context;

    public PrintButtonListener(Context c){
        context = c;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        intent.setComponent(new ComponentName(context, "com.liamgensel.ubprinting.PrintingScreen"));

        context.startActivity(intent);
    }
}
