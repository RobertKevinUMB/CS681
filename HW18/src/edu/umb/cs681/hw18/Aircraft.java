package edu.umb.cs681.hw18;

import java.util.concurrent.atomic.AtomicReference;

public class Aircraft {

    private AtomicReference<Position> position;
    public Position getPosition(){
        return this.position.get();
    }

    public void setPosition(Position newPosition){
        position.set(newPosition);
    }

    public Aircraft(Position position) {
        this.position = new AtomicReference<>(position);
    }

    


}
