package com.liamgensel.ubprinting;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.os.Parcelable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.*;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class PrintingScreen extends AppCompatActivity {

    TextView printText;
    Toolbar toolbar;
    ViewPager viewPager;

    ArrayList<Library> libraries = new ArrayList<Library>();

    TabLayout tabLayout;
    Random r = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_printing_screen);

        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

        libraries.add(new Library("Capen Library",
                "Mon-Thurs 8am-12:00am\nFri 8am-9pm\nSat 9am-5pm\nSun 12pm-12am"
                , new LatLng(43.000725, -78.789724), true, location));
        libraries.add(new Library("Lockwood Library", "Open 24 hours", new LatLng(43.000516, -78.786147), true, location));
        libraries.add(new Library("Blake Center", "Mon-Thurs 9am-12am\nSat 12pm-8pm\nSun 12pm-12am",
                new LatLng(43.008800, -78.785404), false, location));
        libraries.add(new Library("Fronczak Hall", "Mon-Thurs 9am-9pm\nFri 9am-6pm\nSat 9am-5pm\nSun 12pm-5pm",
                new LatLng(43.00125,-78.790497), false, location));
        libraries.add(new Library("Bell Hall", "Open 24 hours",
                new LatLng(43.001526,-78.787042), false, location));
        libraries.add(new Library("Clinton Hall", "Open 24 hours",
                new LatLng(43.002125,-78.794724), false, location));




        libraries = sortTimes(libraries);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        viewPager = (ViewPager) findViewById(R.id.viepager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);

        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);

    }


    public static ArrayList<Library> sortTimes(ArrayList<Library> list){
        int n = list.size();
        int k;
       for(int m = n; m >= 0; m--){
           for (int i = 0; i < n - 1; i++){
               k = i + 1;
               if(list.get(i).getTimeToLibrary() > list.get(k).getTimeToLibrary()) {

                   list = swapLibraries(i, k, list);

               }
            }
       }
        return list;
    }

    public static ArrayList<Library> swapLibraries(int i, int j, ArrayList<Library> list) {

        Library temp;
        temp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, temp);

        return list;
    }


    private class PagerAdapter extends FragmentStatePagerAdapter{

        public PagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            com.liamgensel.ubprinting.Fragment fragment = new com.liamgensel.ubprinting.Fragment();
            Bundle bundle = new Bundle();
            bundle.putParcelable("liam2", (Parcelable) libraries.get(position));
            fragment.setArguments(bundle);
            return fragment;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return libraries.get(position).getName();
        }

        @Override
        public int getCount() {
            return libraries.size();
        }
    }
}
