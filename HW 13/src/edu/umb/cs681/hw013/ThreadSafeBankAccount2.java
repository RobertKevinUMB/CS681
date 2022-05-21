package edu.umb.cs681.hw013;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.Condition;

public class ThreadSafeBankAccount2{
	private double balance = 0;
	private ReentrantLock rl;
	private Condition sufficientFundsCondition;
	private Condition belowUpperLimitFundsCondition;
	private ThreadSafeBankAccount2 acc;
	private int AccountNumber;
	public ThreadSafeBankAccount2(int AccountNumber){
		rl = new ReentrantLock();
		sufficientFundsCondition = rl.newCondition();
		belowUpperLimitFundsCondition = rl.newCondition();
		acc =  this;
		this.AccountNumber = AccountNumber;
	}

	public int getAccountNumber()
	{
		return this.AccountNumber;
	}

	public ReentrantLock getLock()
	{
		return this.rl;
	}

	public void deposit(double amount){
		rl.lock();
		try{
			System.out.println("Thread" +  Thread.currentThread().getId() + 
					" Current balance after deposit: " + balance);

			balance += amount;
			sufficientFundsCondition.signalAll();
			System.out.println("Thread" +  Thread.currentThread().getId() + " New balance after deposit: " + balance);
		}finally{
			rl.unlock();
		}
	}

	public void withdraw(double amount) throws InterruptedException{
		rl.lock();
		try{
			System.out.println("Thread" + Thread.currentThread().getId() + 
					" Current balance after withdrawl: " + balance);
			while(balance <= 0){
				sufficientFundsCondition.await(); 
			}
			if(this.balance > amount){
					this.balance -= amount;
					belowUpperLimitFundsCondition.signalAll();
					System.out.println("Thread" + Thread.currentThread().getId() + " New balance after withdrawl: " + balance);
			} else {
				System.out.println(Thread.currentThread().getId() + " : Insufficient balance");
			}

		}finally{
			rl.unlock();
		}
	}

	public void transfer(ThreadSafeBankAccount2 source,ThreadSafeBankAccount2 destination, double amount) throws InterruptedException
	{
		if(source.getAccountNumber() < destination.getAccountNumber())
		{
			source.getLock().lock();
			destination.getLock().lock();
			try{
				if( this.balance < amount )
				{
					throw new InterruptedException("Balance insufficient");
				}
				else{
					this.withdraw(amount);
					destination.deposit(amount);
				}
			}
			finally{
				destination.getLock().unlock();
				source.getLock().unlock();
			}
		}
		else
		{
			destination.getLock().lock();
			source.getLock().lock();
			try{
				if( this.balance < amount )
				{
					throw new InterruptedException("Balance insufficient");
				}
				else{
					this.withdraw(amount);
					destination.deposit(amount);
				}
			}
			finally{
				source.getLock().unlock();
				destination.getLock().unlock();
			}
		}
	}

	public static void main(String[] args) throws InterruptedException{
		ThreadSafeBankAccount2 ba_1 = new ThreadSafeBankAccount2(9850363);
		ThreadSafeBankAccount2 ba_2 = new ThreadSafeBankAccount2(9850364);

		Thread t1 = null;
		for(int n = 0; n < 5; n++){
			t1 = new Thread( ba_1.new DepositRunnable() );
			t1.start();
		}
		Thread t2 =	new Thread( ba_1.new WithdrawRunnable() );
		t2.start();
		try{
			Thread.sleep(5000);
		}catch (InterruptedException ie) {}
		t1.interrupt();
		t2.interrupt();

		System.out.println("\nThis is transfer");
		ba_1.transfer(ba_1,ba_2,100);
		System.out.println("\nAmount transfer successful");
	}

	private class DepositRunnable implements Runnable{

		public void run(){
			try{
				for(int n = 0; n < 10; n++){
					acc.deposit(100);
					Thread.sleep(2);
				}
			}
			catch(InterruptedException exception){}
		}
	}

	private class WithdrawRunnable implements Runnable{

		public void run(){
			try{
				for(int n = 0; n < 10; n++){
					acc.withdraw(400);
					Thread.sleep(2);
				}
			}
			catch(InterruptedException exception){}
		}
	}
}