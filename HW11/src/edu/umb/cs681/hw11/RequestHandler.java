package edu.umb.cs681.hw11;

import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.Random;
import java.util.concurrent.locks.ReentrantLock;

public class RequestHandler implements Runnable{

    private ReentrantLock lock = new ReentrantLock();
    private boolean flag = false;

    public void setFlag() {
        lock.lock();
        try {
            flag = true;
        }
        finally {
            lock.unlock();
        }
    }

    @Override
    public void run() {

        String[] files = {"AccessCounter.class", "RequestHandler.class", "a.html",
                "b.html", "c.html"};
        AccessCounter instance = AccessCounter.getInstance();

        while (true) {
            lock.lock();
            try {
                if(flag) {
                    System.out.println("No Access");
                    break;
                }

                int index = new Random().nextInt(files.length);
                Path path = FileSystems.getDefault().getPath(".", files[index]);

                instance.increment(path);
                System.out.println(files[index] + instance.getCount(path));
            }
            finally {
                lock.unlock();
            }

            try {
                Thread.sleep(1000);
            }
            catch(InterruptedException e) {
                System.out.println(e.toString());
                continue;
            }
        }

    }

    public static void main(String[] args) {

        for (int i=0; i<13; i++){

            RequestHandler r  = new RequestHandler();
            Thread t1  = new Thread(r);
            Thread t2  = new Thread(r);
            Thread t3  = new Thread(r);
            Thread t4  = new Thread(r);
            t1.start();
            t2.start();
            t3.start();
            t4.start();

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            r.setFlag();
            t1.interrupt();
            t2.interrupt();
            t3.interrupt();
            t4.interrupt();


            try {
                t1.join();
                t2.join();
                t3.join();
                t4.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
