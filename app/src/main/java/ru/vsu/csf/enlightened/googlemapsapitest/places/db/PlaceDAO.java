package ru.vsu.csf.enlightened.googlemapsapitest.places.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;

import java.sql.SQLException;

import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;

public class PlaceDAO extends BaseDaoImpl<Place, Integer>{

    public PlaceDAO(ConnectionSource source) throws SQLException {
        super(source, Place.class);
    }

    public Place getPlaceByName(String name) throws SQLException{
        return queryBuilder().where().eq(Place.PLACE_NAME, name).queryForFirst();
    }
}
