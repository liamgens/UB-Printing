package com.liamgensel.ubprinting;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.view.View;

/**
 * Created by liamgensel on 11/15/15.
 */
public class CybraryButtonListener implements View.OnClickListener {

    Context context;

    public CybraryButtonListener(Context c) {

        context = c;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        intent.setComponent(new ComponentName(context, "com.liamgensel.ubprinting.Cybrary"));

        context.startActivity(intent);
    }
}
