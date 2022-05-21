package edu.umb.cs681.hw10;
import java.util.concurrent.atomic.AtomicReference;


public final class Position implements Runnable {

    private final double latitude, longitude, altitude;

    public Position(double latitude, double longitude, double altitude) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }

    public void run(){  
		Position pos=new Position(this.longitude,this.latitude,this.altitude);
		Aircraft airCraft=new Aircraft(pos);
		airCraft.setPosition(pos);
		System.out.println("Position : "+airCraft.getPosition());
		System.out.println("new position : " + pos.changeAlt(132.500));
	}  

    public static void main(String args[]){  
        Runnable r1 = new Position(32.75,46.2376,127.28);
        Thread th1 = new Thread(r1, "New thread");    	
        th1.start();  
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

    public String toString(){

        return latitude + "," + longitude + ",";

    }

    public boolean equals(Position position2){

        if(this.toString().equals(position2.toString())){
            return true;
        }else{
            return false;
        }
    }

    Position changeLat(double newLat){
        return new Position(newLat, this.longitude, this.altitude);
    }

    Position changeLon(double newLon){
        return new Position(this.latitude, newLon, this.altitude);
    }

    Position changeAlt(double newAlti){
        return new Position(this.latitude, this.longitude, newAlti);
    }
    public class Aircraft {

        private AtomicReference<Position> pos;

        public Aircraft(Position position) {
            pos = new AtomicReference<>(position);
        }

        public Position getPosition() {
            return pos.get();
        }

        public void setPosition(Position position) {
            pos.set(position);
        }
    }  }


