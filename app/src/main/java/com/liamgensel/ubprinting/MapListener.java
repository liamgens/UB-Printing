package com.liamgensel.ubprinting;

import android.content.ComponentName;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

/**
 * Created by liamgensel on 11/14/15.
 */
public class MapListener implements View.OnClickListener{
    private Context context;
    private Library library;
    public MapListener(Context c, Library library){
        context = c;
        this.library = library;
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();

        intent.setComponent(new ComponentName(context, "com.liamgensel.ubprinting.MapsActivity"));
        intent.putExtra("liam", (Parcelable) library);

        context.startActivity(intent);

    }
}
