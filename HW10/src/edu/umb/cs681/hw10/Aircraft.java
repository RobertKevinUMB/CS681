package edu.umb.cs681.hw10;

import java.util.concurrent.atomic.AtomicReference;

public class Aircraft {

    private AtomicReference<Position> position;
    public Position getPosition(){
        return this.position.get();
    }

    public void setPosition(Position newPos){
        position.set(newPos);
    }


    public Aircraft(Position position) {
        this.position = new AtomicReference<>(position);
    }

    

}
