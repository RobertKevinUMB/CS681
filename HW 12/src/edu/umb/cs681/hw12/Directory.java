package edu.umb.cs681.hw12;

import java.time.LocalDateTime;
import java.util.LinkedList;

public class Directory extends FSElement {

    private LinkedList<FSElement> child;

    public Directory(Directory parent, String name, int size, LocalDateTime creationTime) {
        super(parent, name, size, creationTime);
        if(parent != null)
            parent.appendChild(this);
    }

    public LinkedList<FSElement> getChildren() {
        lock.lock();
        try {
            return this.child;
        } finally {
            lock.unlock();
        }
    }

    public void appendChild(FSElement child) {
        if(this.child == null) {
            this.child = new LinkedList<FSElement>();
        }
        this.child.add(child);
    }

    public int countChildren() {
        lock.lock();
        try {
            return getChildren().size();
        } finally {
            lock.unlock();
        }

    }

    public LinkedList<Directory> getSubDirectories() {
        lock.lock();
        try {
            LinkedList<Directory> directories = new LinkedList<Directory>();
            for (FSElement f : getChildren()) {
                if (f instanceof Directory)
                    directories.add((Directory) f);
            }
            return directories;
        } finally {
            lock.unlock();
        }
    }

    public LinkedList<File> getFiles() {
        lock.lock();
        try {
            LinkedList<File> files = new LinkedList<File>();
            for (FSElement f : getChildren()) {
                if (f instanceof File)
                    files.add((File) f);
            }
            return files;
        } finally {
            lock.unlock();
        }

    }

    public int getTotalSize() {
        lock.lock();
        try {
            int sizetotal = 0;
            for(FSElement f : getChildren()) {
                if(f instanceof Directory)
                    sizetotal += ((Directory) f).getTotalSize();
                else
                    sizetotal += f.getSize();
            }
            return sizetotal;
        }finally {
            lock.unlock();
        }

    }

    @Override
    public boolean isDirectory() {
        lock.lock();
        try {
            return true;
        } finally {
            lock.unlock();
        }

    }

}
