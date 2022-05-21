package edu.umb.cs681.hw5;

import java.util.concurrent.locks.ReentrantLock;

public class RunnableCancellablePrimeGenerator extends RunnablePrimeGenerator {
    private boolean done = false;
    private ReentrantLock lock = new ReentrantLock();

    public RunnableCancellablePrimeGenerator(long from, long to) {
        super(from, to);
    }

    public void setDone(){
        lock.lock();
        try {
            done = false;
        } finally {
            lock.unlock();
        }

    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            
            if(done){
                System.out.println("Stop prime numbers.");
                this.primes.clear();
                break;
            }
            if( isPrime(n) ){ this.primes.add(n); }
        }
    }

   
}
