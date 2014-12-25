package ru.vsu.csf.enlightened.googlemapsapitest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;
import ru.vsu.csf.enlightened.googlemapsapitest.places.db.MyDBHelper;
import ru.vsu.csf.enlightened.googlemapsapitest.places.view.PlaceInfoDBRecordAdapter;
import ru.vsu.csf.enlightened.googlemapsapitest.places.view.PlaceInfoViewAdapter;


public class FavoritesActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorites);

        ArrayList<Place> data = null;
        try {
            data = new ArrayList<Place>(MyDBHelper.getInstance(this).getPlaceDAO().getAllPlaces());
            Toast.makeText(this, data.size()+"", Toast.LENGTH_SHORT).show();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        final ListView listView = (ListView) findViewById(R.id.listViewFavorites);
        listView.setAdapter(new PlaceInfoDBRecordAdapter(this, data));

        Button clearFavs = (Button) findViewById(R.id.buttonClearFavorites);
        clearFavs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    MyDBHelper.getInstance(getParent()).getPlaceDAO().clear();
                    ((PlaceInfoDBRecordAdapter)listView.getAdapter()).clear();
                    listView.invalidate();
                    listView.requestLayout();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        });

        Button showFavsOnMap = (Button) findViewById(R.id.buttonShowFavoritesOnMap);
        showFavsOnMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FavoritesActivity.this, MapActivity.class);
                //intent.putExtra("list", (java.io.Serializable) ((PlaceInfoDBRecordAdapter) listView.getAdapter()).getItems());
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.favorites, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }
}
