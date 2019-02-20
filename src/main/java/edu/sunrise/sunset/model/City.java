package edu.sunrise.sunset.model;

public class City {
    private int id;
    private String nameCity;
    private double longitude;
    private double latitude;

    public int getId() {
        return id;
    }

    public String getNameCity() {
        return nameCity;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNameCity(String nameCity) {
        this.nameCity = nameCity;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
}
