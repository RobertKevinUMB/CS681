package edu.umb.cs681.hw07;

public class RunnableConcurrentSingleton implements Runnable {

    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
    }

    public static void main(String[] args) {
        
        Thread t1 = new Thread(new RunnableConcurrentSingleton());
        Thread t2 = new Thread(new RunnableConcurrentSingleton());
        

        t1.start();
        t2.start();
        
    }

}
