package edu.umb.cs681.hw18;

import java.util.concurrent.*;

public class RunnableAircraft implements Runnable{

    @Override
    public void run() {

        Aircraft aircraft = new Aircraft(new Position(90,66,35000));
        System.out.println("Aircraft's position: "+ aircraft.getPosition());

        
        aircraft.setPosition(aircraft.getPosition().changeLat(85));
        System.out.println("Aircraft's updated latitude: "+ aircraft.getPosition().getLatitude());

       
        aircraft.setPosition(aircraft.getPosition().changeLon(63));
        System.out.println("Aircraft's updated longitude: "+ aircraft.getPosition().getLongitude());

        
        aircraft.setPosition(aircraft.getPosition().changeAlt(25000));
        System.out.println("Aircraft's updated altitude: "+ aircraft.getPosition().getAltitude());

       
        System.out.println("Aircraft's updated position: "+ aircraft.getPosition());

    }

    public static void main(String[] args) {

        RunnableAircraft aircraft = new RunnableAircraft();

        ExecutorService executor = Executors.newFixedThreadPool(6);

        executor.execute(aircraft);

        executor.shutdown();

    }

}
