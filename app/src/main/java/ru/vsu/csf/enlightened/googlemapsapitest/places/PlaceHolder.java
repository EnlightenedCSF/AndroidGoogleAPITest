package ru.vsu.csf.enlightened.googlemapsapitest.places;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

public class PlaceHolder {

    private PlaceHolder() {
        places = new ArrayList<Place>();
    }
    private static PlaceHolder instance;
    public static PlaceHolder getInstance() {
        if (instance == null)
            instance = new PlaceHolder();
        return instance;
    }

    private ArrayList<Place> places;

    public ArrayList<Place> getPlaces() {
        return places;
    }


    public void addPlaces(ArrayList<Place> places) {
        this.places.addAll(places);
    }

    public Place getPlace(LatLng location) {
        for (Place place : places) {
            if (place.getLocation().equals(location))
                return place;
        }
        return null;
    }

    public void clearPlaces() {
        places.clear();
    }
}
