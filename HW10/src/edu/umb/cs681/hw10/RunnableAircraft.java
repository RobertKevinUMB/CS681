package edu.umb.cs681.hw10;

public class RunnableAircraft implements Runnable{

    @Override
    public void run() {

        Aircraft aircraft = new Aircraft(new Position(75,60,35000));
        System.out.println("Aircraft's initial position: "+ aircraft.getPosition());

       
        aircraft.setPosition(aircraft.getPosition().changeLat(88));
        System.out.println("Aircraft's updated latitude: "+ aircraft.getPosition().getLatitude());

       
        aircraft.setPosition(aircraft.getPosition().changeLon(67));
        System.out.println("Aircraft's updated longitude: "+ aircraft.getPosition().getLongitude());

        
        aircraft.setPosition(aircraft.getPosition().changeAlt(25000));
        System.out.println("Aircraft's updated altitude: "+ aircraft.getPosition().getAltitude());

       
        System.out.println("Aircraft's updated position: "+ aircraft.getPosition());

    }

    public static void main(String[] args) {

        for(int i=0; i<7; i++) {
            Thread t = new Thread(new RunnableAircraft());
            t.start();
            try {
                t.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

}
