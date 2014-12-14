package ru.vsu.csf.enlightened.googlemapsapitest;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;


public class SearchActivity extends ActionBarActivity {

    public static final String PLACE_NAME = "placeName";
    public static final String PLACE_TYPE = "placeType";

    private EditText placeName;
    private RadioGroup placeType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        placeName = (EditText) findViewById(R.id.editTextPlaceName);
        placeType = (RadioGroup) findViewById(R.id.radioGroupPlaceType);
        Button searchButton = (Button) findViewById(R.id.searchPlacesButton);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (placeType.getCheckedRadioButtonId() == -1)
                    Toast.makeText(SearchActivity.this, "Вы должны выбрать тип заведения!", Toast.LENGTH_LONG).show();
                else {
                    int placeTypeInt;
                    switch (placeType.getCheckedRadioButtonId()) {
                        case R.id.radioCafe0:
                            placeTypeInt = 0;
                            break;
                        case R.id.radioPark1:
                            placeTypeInt = 1;
                            break;
                        case R.id.radioFood2:
                            placeTypeInt = 2;
                            break;
                        case R.id.radioMuseum3:
                            placeTypeInt = 3;
                            break;
                        case R.id.radioLib4:
                            placeTypeInt = 4;
                            break;
                        default:
                            placeTypeInt = 0;
                            break;
                    }

                    Intent intent = new Intent(SearchActivity.this, MapActivity.class);
                    intent.putExtra(PLACE_NAME, placeName.getText().toString());
                    intent.putExtra(PLACE_TYPE, placeTypeInt);
                    startActivity(intent);
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search, menu);
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
