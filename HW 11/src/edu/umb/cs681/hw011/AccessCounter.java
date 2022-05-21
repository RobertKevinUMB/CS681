package edu.umb.cs681.hw011;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

public class AccessCounter {
	private static HashMap<Path, Integer> map = new HashMap<>();
	private ReentrantLock rLk = new ReentrantLock();  

	public void increment(Path path){
		rLk.lock();
		try{
			if(map.containsKey(path))
				map.put(path,map.get(path)+1);
			else
				{map.put(path,1);}
		} finally {
			rLk.unlock();
		}
	}

	public int getCount(Path path){
		rLk.lock();
		int count=0;
		try{
			if(map.containsKey(path))
				count= map.get(path);
		} finally {
			rLk.unlock();
		}
		return count;
	}
	
	private static AccessCounter ac = null;
	private static ReentrantLock rLk1 = new ReentrantLock();
	
	public static AccessCounter getInstance(){
		rLk1.lock();
		try{
			if(ac==null){ ac = new AccessCounter(); }
			return ac;
		}finally{
			rLk1.unlock();
		}
	}



}

