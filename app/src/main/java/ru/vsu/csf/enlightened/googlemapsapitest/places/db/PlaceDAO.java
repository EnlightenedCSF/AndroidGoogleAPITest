package ru.vsu.csf.enlightened.googlemapsapitest.places.db;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;
import java.util.List;

import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;

public class PlaceDAO extends BaseDaoImpl<Place, Integer>{

    public PlaceDAO(ConnectionSource source) throws SQLException {
        super(source, Place.class);
    }

    public List<Place> getAllPlaces() throws SQLException{
        return queryBuilder().query();
    }

    public void clear() throws SQLException {
        TableUtils.clearTable(this.connectionSource, Place.class);
    }
}
