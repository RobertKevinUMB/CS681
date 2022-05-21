package edu.umb.cs681.hw13;

public class Main {

    public static void main(String[] args){

        ThreadSafeBankAccount2 account = new ThreadSafeBankAccount2();
        WithdrawRunnable withdraw = new WithdrawRunnable(account);
        DepositRunnable deposit = new DepositRunnable(account);

        Thread deposit1  = new Thread(deposit);        
        Thread withdraw1  = new Thread(withdraw);        

        deposit1.start();        
        withdraw1.start();
        
        deposit.setDone();
        withdraw.setDone();

        deposit1.interrupt();      
        withdraw1.interrupt();
        

        try{
            deposit1.join();
            withdraw1.join();
            

        }catch (InterruptedException e){
            System.out.println(e.toString());
        }

    }


}
