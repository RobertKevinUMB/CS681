package edu.umb.cs681.hw018;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class RequestHandler implements Runnable {
private AccessCounter access_counter;
private volatile boolean done = false;
	int filePick;
	String target;
	File currDir = new File(".");
	private Random rand;
	private Path path;
	public RequestHandler(AccessCounter access_counter){
		this.access_counter = access_counter;
		rand = new Random();
		filePick = rand.nextInt(20)+1;
	}
	public void setDone(){
		done = true;
	}

	public String selectFile() {
		File dir = new File("./src/hw_18/files");
		  String[] files = dir.list();
		  int random_index = new Random().nextInt(files.length);
		  return (files[random_index]);
	}
	public void run() {
			
		try{
			target = selectFile();
			
			path=Paths.get(target);
			
			Thread.sleep(1);
		} catch (Exception e){
				e.printStackTrace();
		}	

		try{
			access_counter.increment(path);
			System.out.println("\n"+target+", Access count  : "+access_counter.getCount(path));
		} finally {}
		try{
			Thread.sleep(100);
			if(Thread.interrupted()){
				if(done == true){
				System.out.println("\n Interrupted!");
				}
			}else {
				System.out.println("\n"+path.toString()+": Total access: "+access_counter.getCount(path));
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	}
	
	public static void main(String[] args) {
		AccessCounter access = AccessCounter.getInstance();
		ExecutorService exec = Executors.newFixedThreadPool(10);
		try{
			for(int n=0;n<10;n++){
				exec.execute(new RequestHandler(access));
			}
			Thread.sleep(2000);
		}catch (Exception e){
			e.printStackTrace();
		}finally {
			exec.shutdown();
		}
		
	}
}

