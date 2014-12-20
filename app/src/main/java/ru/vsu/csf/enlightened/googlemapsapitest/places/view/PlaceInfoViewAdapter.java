package ru.vsu.csf.enlightened.googlemapsapitest.places.view;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

import ru.vsu.csf.enlightened.googlemapsapitest.R;
import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;

public class PlaceInfoViewAdapter extends ArrayAdapter<PlaceInfoView> {

    private ArrayList<Place> places;


    public PlaceInfoViewAdapter(Context context, ArrayList<Place> places) {
        super(context, R.layout.place_info);
        this.places = places;
    }

    @Override
    public int getCount() {
        return places.size();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PlaceInfoView view = (PlaceInfoView) convertView;

        if (view == null) {
            view = new PlaceInfoView(getContext());
        }

        view.populate(places.get(position));
        return view;
    }
}
