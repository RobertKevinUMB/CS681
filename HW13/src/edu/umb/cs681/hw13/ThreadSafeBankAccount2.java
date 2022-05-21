package edu.umb.cs681.hw13;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeBankAccount2 {

    private double balance = 0;
    private ReentrantLock rlock = new ReentrantLock();

    Condition sufficientFundsCondition= rlock.newCondition();
    Condition belowUpperLimitFundsCondition= rlock.newCondition();

    public ThreadSafeBankAccount2() {
    }

    public void deposit(double amount){

        rlock.lock();
        try{
            while(balance >= 300){
                try {
                    belowUpperLimitFundsCondition.await();
                }catch (InterruptedException e){

                    return;
                }
                balance +=amount;
                System.out.println("Amount deposited: " + amount );
                sufficientFundsCondition.signalAll();
            }
        }finally {
            rlock.unlock();
        }
    }

    public void withdraw(double amount){

        rlock.lock();
        try{
            while(balance <= 0){
                try {
                    sufficientFundsCondition.await();
                }catch (InterruptedException e){

                    return;
                }
                balance -=amount;
                System.out.println("Amount withdrawn: " + amount );
                belowUpperLimitFundsCondition.signalAll();
            }
        }finally {
            rlock.unlock();
        }
    }

}
