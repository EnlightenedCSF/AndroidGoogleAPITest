package ru.vsu.csf.enlightened.googlemapsapitest.places;

import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;

public class Place implements Serializable {

    double latitude;
    double longitude;

    String id;
    String iconUrl;
    String name;

    boolean isOpen;
    int priceLevel;
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
    } //TODO: add picasso^^

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
        this.id = id;
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
