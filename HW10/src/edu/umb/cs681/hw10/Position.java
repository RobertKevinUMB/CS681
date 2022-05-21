package edu.umb.cs681.hw10;

import java.util.ArrayList;
import java.util.Objects;

public final class Position {

    private final double latitude, longitude, altitude;

    public Position(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public double getAltitude() {
        return altitude;
    }

    Position changeLat(double newLat){
        return new Position(newLat, this.longitude, this.altitude);
    }

    Position changeLon(double newLon){
        return new Position(this.latitude, newLon, this.altitude);
    }

    Position changeAlt(double newAlt){
        return new Position(this.latitude, this.longitude, newAlt);
    }


    ArrayList<Double> getCoordinate(){
        ArrayList<Double> coordinates = new ArrayList<>();
        coordinates.add(getLatitude());
        coordinates.add(getLongitude());
        coordinates.add(getAltitude());
        return coordinates;
    }

    public boolean equals(Position p) {
        if (this.toString().equals(p.toString())) {
            return true;
        } else {
            return false;
        }
    }
    @Override
    public String toString() {
        return "Lat:"+this.latitude+"\nLon:"+this.longitude+"\nAlt:"+this.altitude;
    }

}
