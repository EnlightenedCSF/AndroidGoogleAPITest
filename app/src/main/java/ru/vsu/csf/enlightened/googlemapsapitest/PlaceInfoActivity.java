package ru.vsu.csf.enlightened.googlemapsapitest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;
import ru.vsu.csf.enlightened.googlemapsapitest.places.view.PlaceInfoView;


public class PlaceInfoActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.place_info_activity);

        PlaceInfoView placeInfo = (PlaceInfoView) findViewById(R.id.place_info);
        Intent intent = getIntent();
        final Bundle bundle = intent.getExtras();
        if (bundle != null) {

            Place place = new Place() {{
                setName(bundle.getString("name"));
                setLatitude(bundle.getDouble("lat"));
                setLongitude(bundle.getDouble("lon"));
                setAddress(bundle.getString("address"));
                setOpen(bundle.getBoolean("isOpen"));
                setPriceLevel(bundle.getInt("priceLevel"));
                setIconUrl(bundle.getString("iconURL"));

            }};

            Toast.makeText(this, bundle.getString("iconURL"), Toast.LENGTH_LONG).show();

            placeInfo.populate(place);
            Toast.makeText(this, "Success", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.place_info_activity, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }


}
