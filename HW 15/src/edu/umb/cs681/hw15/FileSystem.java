package edu.umb.cs681.hw15;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class FileSystem implements Runnable {

    private LinkedList<Directory> directoryroot;
    private static FileSystem instance = null;

    void appendRootDir(Directory RootDirectory) {
        directoryroot = new LinkedList<Directory>();
        directoryroot.add(RootDirectory);
    }
    private FileSystem() {};

    public static FileSystem getFileSystem() {
        if(instance==null)
            instance = new FileSystem ();
        return instance;
    }

    public LinkedList<Directory> getRootDirs() {
        return this.directoryroot;
    }

    @Override
    public void run() {

        System.out.println("\nThread "+Thread.currentThread().getId()+" is running");

    }

    public static void main(String[] args) {

        LocalDateTime localTime = LocalDateTime.now();

        FileSystem fs = new FileSystem();

        Directory root = new Directory(null, "root", 0, localTime);

        Directory applications = new Directory(root, "applications", 0, localTime);
        File a = new File(applications, "1", 1300, localTime);
        File b = new File(applications, "2", 350, localTime);
        Thread t1 = new Thread(fs);
        t1.start();
        try {
            t1.join();
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
        
        System.out.println("File "+a.getName()+"  added to directory "+root.getName()+".");
        System.out.println("File "+b.getName()+"  added to directory "+root.getName()+".");


        Directory home = new Directory(root, "home", 0, localTime);
        File c = new File(home, "3", 500, localTime);
        File d = new File(home, "4", 700, localTime);
        Thread t2 = new Thread(fs);
        t2.start();
        try {
            t2.join();
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
       
        System.out.println("File "+c.getName()+"  added to  directory "+home.getName()+".");
        System.out.println("File "+d.getName()+"  added to  directory "+home.getName()+".");

        Directory code = new Directory(root, "code", 0, localTime);
        File e = new File(code, "5", 70, localTime);
        File f = new File(code, "6", 130, localTime);
        Thread t3 = new Thread(fs);
        t3.start();
        try {
            t3.join();
        } catch (InterruptedException e3) {
            e3.printStackTrace();
        }
        
        System.out.println("File "+e.getName()+"  added to  directory "+code.getName()+".");
        System.out.println("File "+f.getName()+"  added to  directory "+code.getName()+".");

    }

}
