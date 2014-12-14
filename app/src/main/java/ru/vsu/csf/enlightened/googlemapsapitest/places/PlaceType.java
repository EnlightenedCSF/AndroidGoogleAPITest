package ru.vsu.csf.enlightened.googlemapsapitest.places;

public enum PlaceType {

    CAFE (0),
    AMUSEMENT_PARK (1),
    FOOD (2),
    MUSEUM (3),
    LIBRARY (4)
    ;

    private String value;

    PlaceType(int id) {
        switch (id) {
            case 0:
                this.value = "cafe";
                break;
            case 1:
                this.value = "amusement_park";
                break;
            case 2:
                this.value = "food";
                break;
            case 3:
                this.value = "museum";
                break;
            case 4:
                this.value = "library";
                break;
        }
    }

    public String getValue() {
        return this.value;
    }
}
