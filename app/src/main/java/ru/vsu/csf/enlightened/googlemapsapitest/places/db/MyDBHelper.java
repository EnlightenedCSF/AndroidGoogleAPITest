package ru.vsu.csf.enlightened.googlemapsapitest.places.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;

public class MyDBHelper extends OrmLiteSqliteOpenHelper{

    private static final String DATABASE_NAME = "places2.db";
    private static final int DATABASE_VERSION = 1;

    private PlaceDAO placeDAO;

    public MyDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            TableUtils.createTable(connectionSource, Place.class);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
            TableUtils.dropTable(connectionSource, Place.class, true);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        onCreate(database, connectionSource);
    }

    public PlaceDAO getPlaceDAO() throws SQLException {
        if (placeDAO == null)
            placeDAO = new PlaceDAO(getConnectionSource());
        return placeDAO;
    }

    public static MyDBHelper getInstance(Context context) {
        return OpenHelperManager.getHelper(context, MyDBHelper.class);
    }
}
