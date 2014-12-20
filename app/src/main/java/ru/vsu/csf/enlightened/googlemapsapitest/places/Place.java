package ru.vsu.csf.enlightened.googlemapsapitest.places;

import com.google.android.gms.maps.model.LatLng;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;

@DatabaseTable(tableName="places")
public class Place implements Serializable {

    public static final String PLACE_ID = "place_id";
    public static final String PLACE_ID_TEXT = "place_id_text";
    public static final String PLACE_NAME = "place_name";
    public static final String PLACE_LAT = "place_lat";
    public static final String PLACE_LNG = "place_lng";
    public static final String PLACE_ICON_URL = "place_icon_url";
    public static final String PLACE_PRICE_LVL = "place_price_lvl";
    public static final String PLACE_ADDRESS = "place_address";

    @DatabaseField(columnName = PLACE_ID_TEXT)
    String textId;
    @DatabaseField(columnName = PLACE_ID, generatedId = true)
    int id;
    @DatabaseField(columnName = PLACE_NAME)
    String name;

    @DatabaseField(columnName = PLACE_LAT)
    double latitude;
    @DatabaseField(columnName = PLACE_LNG)
    double longitude;

    @DatabaseField(columnName = PLACE_ICON_URL)
    String iconUrl;

    boolean isOpen;
    @DatabaseField(columnName = PLACE_PRICE_LVL)
    int priceLevel;
    @DatabaseField(columnName = PLACE_ADDRESS)
    String address;

    @Override
    public String toString() {
        return name + "\n" +
                "IsOpen: " + isOpen + "\n" +
                "PriceLevel: " + priceLevel + "\n" +
                "Address: " + address + "\n";
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public String getName() {
        return name;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public int getPriceLevel() {
        return priceLevel;
    }

    public String getAddress() {
        return address;
    }

    public LatLng getLocation() {
        return new LatLng(this.latitude, this.longitude);
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setId(String id) {
        this.textId = id;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

    public void setPriceLevel(int priceLevel) {
        this.priceLevel = priceLevel;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
