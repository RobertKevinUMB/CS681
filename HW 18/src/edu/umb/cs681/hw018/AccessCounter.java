package edu.umb.cs681.hw018;

import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	final ConcurrentMap<Path, AtomicLong> counterMap = new ConcurrentHashMap<>();
	private ReentrantLock rlk = new ReentrantLock();  

	public void increment(Path path){
		rlk.lock();
		try{
			counterMap.computeIfAbsent(path, p -> new AtomicLong()).incrementAndGet();
		} finally {
			rlk.unlock();
		}
	}

	public long getCount(Path path){
		rlk.lock();
		try{
			AtomicLong al = counterMap.get(path);
		    return al == null ? 0 : al.get();
		} finally {
			rlk.unlock();
		}
	}
	
	private static AccessCounter ac = null;
	private static ReentrantLock rlk1 = new ReentrantLock();
	
	public static AccessCounter getInstance(){
		rlk1.lock();
		try{
			if(ac==null){ ac = new AccessCounter(); }
			return ac;
		}finally{
			rlk1.unlock();
		}
	}



}

