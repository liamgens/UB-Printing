package com.liamgensel.ubprinting;

import android.location.Location;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.Random;

/**
 * Created by liamgensel on 11/14/15.
 */
public class Library implements Parcelable{
    String name, hours;
    LatLng coordinates;
    boolean color;
    int waitTime;
    Location libraryLocation;
    Random r = new Random();
    float timeToLibrary;

    public Library(String name, String hours, LatLng coordinates, boolean color, Location myLocation) {
        this.name = name;
        this.hours = hours;
        this.coordinates = coordinates;
        this.color = color;
        final int x = r.nextInt(45);
        this.waitTime = x;

        libraryLocation = new Location(myLocation);
        libraryLocation.setLatitude(coordinates.latitude);
        libraryLocation.setLongitude(coordinates.longitude);

        float milesToLibrary = (libraryLocation.distanceTo(myLocation)) * .000621371f;
        timeToLibrary = (waitTime) + (milesToLibrary*15);
    }



    public static final Creator<Library> CREATOR = new Creator<Library>() {
        @Override
        public Library createFromParcel(Parcel in) {
            return new Library(in);
        }

        @Override
        public Library[] newArray(int size) {
            return new Library[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getHours() {
        return hours;
    }

    public int getWaitTime() {
        return waitTime;
    }

    public LatLng getCoordinates() {
        return coordinates;
    }

    public boolean isColor() {
        return color;
    }

    public float getTimeToLibrary(){
        return timeToLibrary;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(hours);
        dest.writeParcelable(coordinates, flags);
        dest.writeByte((byte) (color ? 1 : 0));
        dest.writeInt(waitTime);
        dest.writeParcelable(libraryLocation, flags);
        dest.writeFloat(timeToLibrary);
    }

    protected Library(Parcel in) {
        name = in.readString();
        hours = in.readString();
        coordinates = in.readParcelable(LatLng.class.getClassLoader());
        color = in.readByte() != 0;
        waitTime = in.readInt();
        libraryLocation = in.readParcelable(Location.class.getClassLoader());
        timeToLibrary = in.readFloat();
    }
}
