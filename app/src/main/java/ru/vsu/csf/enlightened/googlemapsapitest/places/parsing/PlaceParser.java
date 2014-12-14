package ru.vsu.csf.enlightened.googlemapsapitest.places.parsing;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;

public class PlaceParser implements IParser<Place>, Serializable {
    /*
    {
       "debug_info" : [],
       "html_attributions" : [
          "Listings by \u003ca href=\"http://www.yellowpages.com.au/\"\u003eYellow Pages\u003c/a\u003e"
       ],
       "next_page_token" : "ClRIAAAA81cWfi0WTUUKB9gszCYq9df7mStFyoCOu--Kd9Dv8_U7Iljt-DJygDnlXRvy0fsOr5TMgqgoOIeYZ1bJ1VLFyouxZ7guHnI7796Nv66mt7USEE1yVN0OZ3dAl6-wFk0LAlcaFFIxt3tpEKf4SdHth2gHH4f8Tx_y",
       "results" : [
          {
             "geometry" : {
                "location" : {
                   "lat" : -33.867260,
                   "lng" : 151.1958130
                }
             },
             "icon" : "http://maps.gstatic.com/mapfiles/place_api/icons/bar-71.png",
             "id" : "7eaf747a3f6dc078868cd65efc8d3bc62fff77d7",
             "name" : "Biaggio Cafe",
             "opening_hours" : {
                "open_now" : true
             },
             "photos" : [
                {
                   "height" : 600,
                   "html_attributions" : [],
                   "photo_reference" : "CoQBegAAAIDq6dN22YIkR6IVd_zW9Q-r4-Pn4gElH4iETwtiQIjBUhPd6ItDuB9T_wBPCwzPDwB6NXqDZdNMORHzs6yGyitV0bzuP9jrsBjjHHjT6VAyYnMFJe9iPAlcMN6Bnv-EgTSyGibgJPV4P7kODba8AVYKW7jiPxTw1MSV065SRlb5EhAXnIrTWuEyLZxkC0Dk7ccyGhRWdTai1EdGsQnLJJygZpxRSkj_sg",
                   "width" : 900
                }
             ],
             "price_level" : 1,
             "reference" : "CnRqAAAAtzxgHoMgRt4nfsgJrK5lCawM_zKjNB-j1IVjh2Un58RyRY2rnR7w5T5s-43qjiwBx2dcnRwnhCZxy4o7zdGC5qiS3zNb0OJz2okMJgNXhwGLRuzvszJelTVTpZKW3ZT__lo0xjZLO1RC5e0WGAC5FBIQGj-j1KCCj5KhZiceDwLwQhoUtPmhjIMcsUA5KkqzbxdqWZBM7oU",
             "types" : [ "bar", "restaurant", "food", "establishment" ],
             "vicinity" : "48 Pirrama Road, Pyrmont"
          }
       ]
    }
    * */

    @Override
    public ArrayList<Place> parse(String json) throws JSONException {
        final ArrayList<Place> places = new ArrayList<Place>();
        System.out.println("\n\n\n" + json + "\n\n\n");
        JSONObject response = new JSONObject(json);

        JSONArray results = response.getJSONArray("results");
        for (int i = 0; i < results.length(); i++) {

            final JSONObject placeObj = results.getJSONObject(i);

            places.add(new Place() {{
                JSONObject location = placeObj.getJSONObject("geometry").getJSONObject("location");

                setLatitude(location.getDouble("lat"));
                setLongitude(location.getDouble("lng"));

                setIconUrl(placeObj.getString("icon"));
                setId(placeObj.getString("id"));
                setName(placeObj.getString("name"));
                setOpen(placeObj.optJSONObject("opening_hours") != null && placeObj.getJSONObject("opening_hours").getBoolean("open_now"));
                setPriceLevel(placeObj.optInt("price_level", 5));
                setAddress(placeObj.getString("vicinity"));
            }});
        }

        return places;
    }
}
