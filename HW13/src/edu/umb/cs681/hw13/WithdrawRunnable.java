package edu.umb.cs681.hw13;

import java.util.concurrent.locks.ReentrantLock;

public class WithdrawRunnable implements Runnable{

    private ThreadSafeBankAccount2 account;
    private ReentrantLock lock = new ReentrantLock();
    public boolean done = false;

    public WithdrawRunnable(ThreadSafeBankAccount2 account) {
        this.account = account;
    }

    public void setDone(){
        lock.lock();
        try{
            this.done = true;
        }finally {
            lock.unlock();
        }
    }

    public void run(){

        while(true){

            if (done == true){
                break;
            }
            account.withdraw(250);
            try{
                Thread.sleep(2000);
            }catch (InterruptedException e){
                System.out.println(e.toString());
                continue;
            }
        }
    }
}
