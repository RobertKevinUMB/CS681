package edu.umb.cs681.hw18;

import java.util.ArrayList;

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
    Position changeLat(double newLatitude){
        return new Position(newLatitude, this.longitude, this.altitude);
    }

    Position changeLon(double newLongitude){
        return new Position(this.latitude, newLongitude, this.altitude);
    }

    Position changeAlt(double newAltitude){
        return new Position(this.latitude, this.longitude, newAltitude);
    }

    ArrayList<Double> getCoordinate(){
        ArrayList<Double> coordinates = new ArrayList<>();
        coordinates.add(getLatitude());
        coordinates.add(getLongitude());
        coordinates.add(getAltitude());
        return coordinates;
    }

    @Override
    public String toString() {
        return "\nLat:"+this.latitude+"\nLon:"+this.longitude+"\nAlt:"+this.altitude;
    }

    public boolean equals(Position p) {
        if (this.toString().equals(p.toString())) {
            return true;
        } else {
            return false;
        }
    }

  

}
