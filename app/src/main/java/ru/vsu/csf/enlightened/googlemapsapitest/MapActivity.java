package ru.vsu.csf.enlightened.googlemapsapitest;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesClient;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationClient;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.json.JSONException;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

import ru.vsu.csf.enlightened.googlemapsapitest.maps.GoogleMapFragment;
import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;
import ru.vsu.csf.enlightened.googlemapsapitest.places.PlaceHolder;
import ru.vsu.csf.enlightened.googlemapsapitest.places.parsing.PlaceParser;
import ru.vsu.csf.enlightened.googlemapsapitest.places.PlaceType;


public class MapActivity extends FragmentActivity implements GooglePlayServicesClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {

    private final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

    private String placeName;
    private PlaceType placeType;

    private GoogleMapFragment googleMapFragment;
    private LocationClient locationClient;
    private PlaceHolder placeHolder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        initMap();

        locationClient = new LocationClient(this, this, this);
        placeHolder = PlaceHolder.getInstance();

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        if (b != null) {
            placeName = b.getString(SearchActivity.PLACE_NAME);
            placeType = PlaceType.values()[b.getInt(SearchActivity.PLACE_TYPE)];
            Toast.makeText(MapActivity.this, placeName + " " + b.getInt(SearchActivity.PLACE_TYPE) + " " + placeType.getValue(), Toast.LENGTH_SHORT).show();
        }

        //SearchPlaces();
    }

    //region Map stuff
    private void initMap() {
        FragmentManager fm = getSupportFragmentManager();
        googleMapFragment = (GoogleMapFragment) fm.findFragmentByTag(GoogleMapFragment.TAG);
        if (googleMapFragment == null) {
            googleMapFragment = new GoogleMapFragment();
            fm.beginTransaction().replace(R.id.map, googleMapFragment, GoogleMapFragment.TAG).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        return id == R.id.action_settings || super.onOptionsItemSelected(item);
    }

    //endregion

    //region Places stuff

    Connection connector;

    public void SearchPlaces() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = connectivityManager.getActiveNetworkInfo();
        if (info != null && info.isConnected()){
            connector = new Connection();
            connector.execute();
        }
        else {
            Toast.makeText(MapActivity.this, "Is not connected!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onConnected(Bundle bundle) {
        Toast.makeText(this, "Connected", Toast.LENGTH_SHORT).show();
        SearchPlaces();
    }

    @Override
    public void onDisconnected() {
        Toast.makeText(this, "Disconnected", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onConnectionFailed(ConnectionResult connectionResult) {
        /*
         * Google Play services can resolve some errors it detects.
         * If the error has a resolution, try sending an Intent to
         * start a Google Play services activity that can resolve
         * error.
         */
        if (connectionResult.hasResolution()) {
            try {
                // Start an Activity that tries to resolve the error
                connectionResult.startResolutionForResult(
                        this,
                        CONNECTION_FAILURE_RESOLUTION_REQUEST);
                /*
                 * Thrown if Google Play services canceled the original
                 * PendingIntent
                 */
            } catch (IntentSender.SendIntentException e) {
                // Log the error
                e.printStackTrace();
            }
        } else {
            /*
             * If no resolution is available, display a dialog to the
             * user with the error.
             */
            //showErrorDialog(connectionResult.getErrorCode());
            Toast.makeText(this, connectionResult.getErrorCode(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        locationClient.connect();
    }

    @Override
    protected void onStop() {
        locationClient.disconnect();
        super.onStop();
    }


    class Connection extends AsyncTask<String, Void, String> {

        public final static int READ_TIMEOUT = 10000;
        public final static int CONNECT_TIMEOUT = 15000;
        public final static String REQUEST_METHOD = "GET";

        public String result;

        public Connection() {
            super();
        }

        @Override
        protected String doInBackground(String... strings) {

            try {
                Location currentLocation = locationClient.getLastLocation();

                URL url = new URL("https://maps.googleapis.com/maps/api/place/nearbysearch/json?" +
                        "types=" + placeType.getValue() + "&" +
                        //(placeName.isEmpty() ? "" : "name=" + placeName + "&") +
                        "radius=1000&" +
                        "language=ru&" +
                        "location=" + currentLocation.getLatitude() + "," + currentLocation.getLongitude() + "&" +
                        "sensor=false&" +
                        "key=AIzaSyDKJgfomc0h2SPZhI9m8amXKr7YKQpzwgU");
                HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
                connection.setConnectTimeout(CONNECT_TIMEOUT);
                connection.setReadTimeout(READ_TIMEOUT);
                connection.setRequestMethod(REQUEST_METHOD);
                connection.setDoInput(true);
                connection.connect();

                InputStream is = connection.getInputStream();

                Scanner scanner = new Scanner(is);
                String str = "";
                String line;
                while (scanner.hasNext()) {
                    line = scanner.nextLine();
                    str += line;
                }

                return str;

            } catch (Exception e) {
                e.printStackTrace();
            }

            return "Connection error!";
        }

        @Override
        protected void onPostExecute(String s) {
            result = s;
            try {
                PlaceParser parser = new PlaceParser();
                ArrayList<Place> places = parser.parse(result);
                placeHolder.addPlaces(parser.parse(result));
                for (Place p : places) {
                    googleMapFragment.map.addMarker(new MarkerOptions()
                            .position(new LatLng(p.getLatitude(), p.getLongitude()))
                            .title(p.getName())
                            .snippet(p.getAddress())
                    );
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    //endregion
}
