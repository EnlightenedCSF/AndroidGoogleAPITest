package ru.vsu.csf.enlightened.googlemapsapitest.places.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ru.vsu.csf.enlightened.googlemapsapitest.R;
import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;

public class PlaceInfoDBRecordAdapter extends ArrayAdapter<PlaceInfoDBRecord>{
    private ArrayList<Place> places;


    public PlaceInfoDBRecordAdapter(Context context, ArrayList<Place> places) {
        super(context, R.layout.place_info);
        this.places = places;
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlaceInfoDBRecord view = (PlaceInfoDBRecord) convertView;

        if (view == null) {
            view = new PlaceInfoDBRecord(getContext());
        }

        view.populate(places.get(position));
        return view;
    }

    public void clear() {
        places.clear();
    }
}
