package edu.umb.cs681.hw07;

import java.util.concurrent.locks.ReentrantLock;

public class ConcurrentSingleton {
    private static ConcurrentSingleton instance = null;
    private  ConcurrentSingleton(){ }
    private static ReentrantLock lk = new ReentrantLock();

    

    public static ConcurrentSingleton getInstance(){
        lk.lock();
        try {
            if (instance == null)
                instance = new ConcurrentSingleton();
            return instance;
        } finally {
            lk.unlock();
        }
    }
}
