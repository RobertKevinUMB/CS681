package edu.umb.cs681.hw9;

import java.util.concurrent.atomic.AtomicReference;

public class ConcurrentSingleton implements Runnable {

    private ConcurrentSingleton() {};
    private static AtomicReference<ConcurrentSingleton> instance = new AtomicReference<ConcurrentSingleton>(null);

    public static AtomicReference<ConcurrentSingleton> getInstance() {
        instance.updateAndGet((ConcurrentSingleton ref)->{if(instance.get()==null)
        {instance.set(new ConcurrentSingleton());}return ref;});
        return instance;
    }
    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
    }

    public static void main(String[] args) {

        for(int i=0; i<10; i++){
            new Thread(new ConcurrentSingleton()).start();
        }
    }
}
