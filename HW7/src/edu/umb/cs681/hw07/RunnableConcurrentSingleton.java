package edu.umb.cs681.hw07;

public class RunnableConcurrentSingleton implements Runnable {

    public void run() {
        System.out.println(ConcurrentSingleton.getInstance());
    }

    public static void main(String[] args) {
        
        Thread th1 = new Thread(new RunnableConcurrentSingleton());
        Thread th2 = new Thread(new RunnableConcurrentSingleton());
        Thread th3 = new Thread(new RunnableConcurrentSingleton());

        th1.start();
        th2.start();
        th3.start();
    }

}
