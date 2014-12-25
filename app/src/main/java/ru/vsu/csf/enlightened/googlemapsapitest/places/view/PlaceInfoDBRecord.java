package ru.vsu.csf.enlightened.googlemapsapitest.places.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import ru.vsu.csf.enlightened.googlemapsapitest.R;
import ru.vsu.csf.enlightened.googlemapsapitest.places.Place;

public class PlaceInfoDBRecord extends RelativeLayout{

    private TextView mName;
    private ImageView mIcon;
    private TextView mPriceLevel;
    private TextView mAddress;
    private Button mDeleteRecordButton;

    public PlaceInfoDBRecord(Context context) {
        super(context);
        init(context, null);
    }

    public PlaceInfoDBRecord(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(getContext(), R.layout.place_info_db, this);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PlaceInfoDBRecord, 0, 0);

        try {
            String name = array.getString(R.styleable.PlaceInfoDBRecord_place_name_db);
            int priceLevel  = array.getInt(R.styleable.PlaceInfoDBRecord_place_price_level_db, 5);
            String address = array.getString(R.styleable.PlaceInfoDBRecord_place_address_db);
            Drawable icon  = array.getDrawable(R.styleable.PlaceInfoDBRecord_place_icon_db);
            if (icon == null)
                icon = getResources().getDrawable(R.drawable.ic_launcher);

            mName = (TextView) findViewById(R.id.place_name_db);
            mIcon = (ImageView) findViewById(R.id.place_icon_db);
            mPriceLevel = (TextView) findViewById(R.id.place_price_level_db);
            mAddress = (TextView) findViewById(R.id.place_address_db);

            mName.setText(name);
            mIcon.setImageDrawable(icon);

            switch (priceLevel) {
                case 0:
                    mPriceLevel.setText("Бесплатное");
                    break;
                case 1:
                    mPriceLevel.setText("Дешевое");
                    break;
                case 2:
                    mPriceLevel.setText("Средней стоимости");
                    break;
                case 3:
                    mPriceLevel.setText("Дорогое");
                    break;
                case 4:
                    mPriceLevel.setText("Элитное");
                    break;
                case 5:
                    mPriceLevel.setText("Не указано");
                    break;
            }

            mAddress.setText(address);

            invalidate();
            requestLayout();
        }
        finally {
            array.recycle();
        }
    }

    public void populate(Place place) {
        mName.setText(place.getName());

        Picasso.with(getContext()).load(place.getIconUrl()).into(mIcon);

        switch (place.getPriceLevel()) {
            case 0:
                mPriceLevel.setText("Бесплатное");
                break;
            case 1:
                mPriceLevel.setText("Дешевое");
                break;
            case 2:
                mPriceLevel.setText("Средней стоимости");
                break;
            case 3:
                mPriceLevel.setText("Дорогое");
                break;
            case 4:
                mPriceLevel.setText("Элитное");
                break;
            case 5:
                mPriceLevel.setText("Неизвестно");
                break;
        }
        mAddress.setText(place.getAddress());

        invalidate();
        requestLayout();
    }
}
