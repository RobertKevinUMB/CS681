package edu.umb.cs681.hw14;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

    private ConcurrentHashMap<Path, AtomicInteger> hashMap = new ConcurrentHashMap<Path, AtomicInteger>();
    private static ReentrantLock staticLock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {
    }

    public int getCount(Path path) {
        return hashMap.get(path).incrementAndGet();
    }

    public void increment(Path path) {
        hashMap.putIfAbsent(path, new AtomicInteger(1));
    }
    public static AccessCounter getInstance() {
        staticLock.lock();
        try {
            if (instance == null)
                instance = new AccessCounter();
            return instance;
        } finally {
            staticLock.unlock();
        }
    }

    
}

