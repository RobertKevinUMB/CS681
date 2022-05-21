package edu.umb.cs681.hw014;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	final ConcurrentMap<Path, AtomicLong> counterMap = new ConcurrentHashMap<>();
	private ReentrantLock lock = new ReentrantLock();   

	public void increment(Path path){
		lock.lock();
		try{
			counterMap.computeIfAbsent(path, p -> new AtomicLong()).incrementAndGet();
		} finally {
			lock.unlock();
		}
	}

	public long getCount(Path path){
		lock.lock();
		try{
			AtomicLong al = counterMap.get(path);
		    return al == null ? 0 : al.get();
		} finally {
			lock.unlock();
		}
	}
	
	private static AccessCounter ac = null;
	private static ReentrantLock rl1 = new ReentrantLock();
	public static AccessCounter getInstance(){
		rl1.lock();
		try{
			if(ac==null){ ac = new AccessCounter(); }
			return ac;
		}finally{
			rl1.unlock();
		}
	}



}

