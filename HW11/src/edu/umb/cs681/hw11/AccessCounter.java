package edu.umb.cs681.hw11;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {

    private Map<Path, Integer> hashMap = new HashMap<Path, Integer>();
    private ReentrantLock rLock = new ReentrantLock();
    private static ReentrantLock staticLock = new ReentrantLock();
    private static AccessCounter instance = null;

    private AccessCounter() {};

    public static AccessCounter getInstance() {
        staticLock.lock();
        try {
            if (instance == null)
                instance = new AccessCounter();
            return instance;
        }
        finally {
            staticLock.unlock();
        }
    }

    public void increment(Path path) {
        rLock.lock();
        try {
            if (hashMap.get(path) != null)
                hashMap.put(path, hashMap.get(path) + 1);
            else
                hashMap.put(path, 1);
        }
        finally {
            rLock.unlock();
        }
    }

    public int getCount(Path path) {
        rLock.lock();
        try {
            if (hashMap.get(path) != null)
                return hashMap.get(path);
            else
                return 0;
        }
        finally {
            rLock.unlock();
        }
    }

}
