package ru.vsu.csf.enlightened.googlemapsapitest.maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Marker;

import ru.vsu.csf.enlightened.googlemapsapitest.PlaceInfoActivity;
import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;
import ru.vsu.csf.enlightened.googlemapsapitest.places.PlaceHolder;

public class GoogleMapFragment extends SupportMapFragment {

    public static final String TAG = GoogleMapFragment.class.getSimpleName();
    public GoogleMap map;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setUpMapIfNeeded();
    }

    private void initMap() {
        map.clear();
        map.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        map.getUiSettings().setAllGesturesEnabled(true);
        map.getUiSettings().setZoomGesturesEnabled(true);
        map.getUiSettings().setZoomControlsEnabled(true);
        map.getUiSettings().setCompassEnabled(false);
        map.setMyLocationEnabled(true);
        map.getUiSettings().setMyLocationButtonEnabled(true);
        map.getUiSettings().setRotateGesturesEnabled(false);
    }

    @Override
    public void onResume() {
        super.onResume();
        setUpMapIfNeeded();
    }

    private void initListener() {
        map.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener() {
            @Override
            public void onInfoWindowClick(Marker marker) {
                try {
                    Place place = PlaceHolder.getInstance().getPlace(marker.getPosition());
                    if (place == null) {
                        Toast.makeText(getActivity(),  "ERROR", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        Intent intent = new Intent(getActivity(), PlaceInfoActivity.class);
                        intent.putExtra("name", place.getName());
                        intent.putExtra("address", place.getAddress());
                        intent.putExtra("isOpen", place.isOpen());
                        intent.putExtra("priceLevel", place.getPriceLevel());
                        intent.putExtra("iconURL", place.getIconUrl());
                        startActivity(intent);
                    }
                }
                catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void setUpMapIfNeeded() {
        if (map == null) {
            map = getMap();
            if (map != null) {
                initMap();
                initListener();
            }
        }
    }
}