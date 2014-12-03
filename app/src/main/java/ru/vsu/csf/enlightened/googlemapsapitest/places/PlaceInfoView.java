package ru.vsu.csf.enlightened.googlemapsapitest.places;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import ru.vsu.csf.enlightened.googlemapsapitest.R;

public class PlaceInfoView extends LinearLayout {

    private ImageView mIcon;
    private TextView mName;
    private TextView mIsOpen;
    private TextView mAddress;
    private TextView mPriceLevel;

    public PlaceInfoView(Context context) {
        super(context);
        init(null, context);
    }

    public PlaceInfoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs, context);
    }

    protected void init(AttributeSet attrs, Context context) {
        inflate(getContext(), R.layout.place_info, this);

        TypedArray array = context.getTheme().obtainStyledAttributes(attrs, R.styleable.PlaceInfoView, 0, 0);

        try {
            String name = array.getString(R.styleable.PlaceInfoView_place_name);
            boolean isOpen = array.getBoolean(R.styleable.PlaceInfoView_place_is_open, false);
            int priceLevel  = array.getInt(R.styleable.PlaceInfoView_place_price_level, 5);
            String address = array.getString(R.styleable.PlaceInfoView_place_address);
            Drawable icon  = array.getDrawable(R.styleable.PlaceInfoView_place_icon);
            if (icon == null)
                icon = getResources().getDrawable(R.drawable.ic_launcher);

            mName = (TextView) findViewById(R.id.place_name);
            mIcon = (ImageView) findViewById(R.id.place_icon);
            mIsOpen = (TextView) findViewById(R.id.place_is_open);
            mPriceLevel = (TextView) findViewById(R.id.place_price_level);
            mAddress = (TextView) findViewById(R.id.place_address);

            mName.setText(name);
            if (isOpen) {
                mIsOpen.setText("Да");
                mIsOpen.setTextColor(Color.GREEN);
            }
            else {
                mIsOpen.setText("Нет");
                mIsOpen.setTextColor(Color.RED);
            }
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
                    mPriceLevel.setText("Неизвестно");
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
        mIcon.setImageDrawable(getResources().getDrawable(R.drawable.ic_launcher));
        if (place.isOpen()) {
            mIsOpen.setText("Да");
            mIsOpen.setTextColor(Color.GREEN);
        }
        else {
            mIsOpen.setText("Нет");
            mIsOpen.setTextColor(Color.RED);
        }
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
