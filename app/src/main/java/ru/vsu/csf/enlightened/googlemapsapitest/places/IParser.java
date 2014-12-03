package ru.vsu.csf.enlightened.googlemapsapitest.places;

import org.json.JSONException;

import java.io.Serializable;
import java.util.ArrayList;

public interface IParser<T extends Serializable>{

    public ArrayList<T> parse(String json) throws JSONException;
}
