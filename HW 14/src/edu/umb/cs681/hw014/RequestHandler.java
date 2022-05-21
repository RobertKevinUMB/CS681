package edu.umb.cs681.hw014;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class RequestHandler implements Runnable {
private AccessCounter ac;
private volatile boolean done = false;
	int filePick;
	String targetFile;
	File currDir = new File(".");
	String prefix;
	String suffix=".txt";
	private final int total=20;
	private Random rand;
	private Path path;
	public RequestHandler(AccessCounter ac){
		this.ac = ac;
		rand = new Random();
		filePick = rand.nextInt(total)+1;
	}
	public void setDone(){
		done = true;
	}

	public String selectFile() {
		File dir = new File("./src/hw_14/files");
		  String[] files = dir.list();
		  int random_index = new Random().nextInt(files.length);
		  return (files[random_index]);
	}
	public void run() {
			
		try{
			targetFile = selectFile();
			System.out.println("\nExecuting the handler for file "+targetFile);
			
			path=Paths.get(targetFile);
			
			TimeUnit.SECONDS.sleep(1);
		} catch (Exception e){
				e.printStackTrace();
		}	

		try{
			ac.increment(path);
			System.out.println("\n"+targetFile+", current access count: "+ac.getCount(path));
		} finally {}
		try{
			TimeUnit.MILLISECONDS.sleep(100);
			if(Thread.interrupted()){
				if(done == true){
				System.out.println("\nDone is true.Thread for file: "+targetFile+" is interrupted!");
				}
			}else {
				System.out.println("\n"+path.toString()+": total access: "+ac.getCount(path));
			}
		}catch (InterruptedException e) {
			e.printStackTrace();
		}
	    
	}
	
	public static void main(String[] args) {

		final int total=10;
		AccessCounter ac = AccessCounter.getInstance();
		try{
			for(int i=0;i<total;i++){
				Thread thread1 = new Thread(new RequestHandler(ac));
			    thread1.start();
			}
			TimeUnit.SECONDS.sleep(5);
		}catch (Exception e){
			e.printStackTrace();
		}
	
	}
}

