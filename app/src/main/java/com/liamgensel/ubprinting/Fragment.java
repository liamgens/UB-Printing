package com.liamgensel.ubprinting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by liamgensel on 11/14/15.
 */
public class Fragment extends android.support.v4.app.Fragment{
    Library library;

    TextView name, time, hours, color;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        ViewGroup rootView = (ViewGroup) inflater.inflate(R.layout.fragment, container, false);


        library = (Library) getArguments().getParcelable("liam2");

        name = (TextView) rootView.findViewById(R.id.fragment_name);
        time = (TextView) rootView.findViewById(R.id.fragment_time);
        hours = (TextView) rootView.findViewById(R.id.fragment_hours);
        color = (TextView) rootView.findViewById(R.id.fragment_color);

        name.setText(library.getName());
        time.setText((Math.round(library.getTimeToLibrary())  + " minutes"));
        hours.setText(library.getHours());
        if(library.isColor()){color.setText("Yes");}else {color.setText("No");}


        Button showMap = (Button)rootView.findViewById(R.id.showMap);
        showMap.setOnClickListener(new MapListener(getContext(), library));
        return rootView;
    }
}
