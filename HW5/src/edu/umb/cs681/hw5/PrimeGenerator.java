package edu.umb.cs681.hw5;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

public class PrimeGenerator {
    protected long from, to;
    protected LinkedList<Long> primes = new LinkedList<Long>();

    public PrimeGenerator(long from, long to){
        if(from >= 1 && to > from){
            this.from = from;
            this.to = to;
        }else{
            throw new RuntimeException("Wrong input values: from=" + from + " to=" + to);
        }
    }

    public LinkedList<Long> getPrimes(){ return primes; };

    
    protected boolean isPrime(long n){
        
        if(n <= 1){ return false; }
        
        if( n > 2 && isEven(n) ){ return false; }
        long i;
        
        for (i = (long) Math.sqrt(n); n%i != 0 && i >= 1; i--){}
        
        if (i == 1){ return true; }
        else{ return false; }
    }
    protected boolean isEven(long n){
        if(n%2 == 0){ return true; }
        else{ return false; }
    }

    public void generatePrimes(){
        for (long n = from; n <= to; n++) {
            if( isPrime(n) ){ primes.add(n); }
        }
    }

    public static void main(String[] args) {
        
        PrimeGenerator g = new PrimeGenerator(1, 100);
        g.generatePrimes();
        g.getPrimes().forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + g.getPrimes().size() + " prime numbers are found.");

        
        PrimeGenerator g1 = new PrimeGenerator(1, 100);
        List<Long> primes = LongStream.rangeClosed(g1.from, g1.to)
                .filter( (long n)->g1.isPrime(n) )
                .boxed()
                .collect(Collectors.toList());
        primes.forEach( (Long prime)-> System.out.print(prime + ", ") );
        System.out.println("\n" + primes.size() + " prime numbers are found.");

        
        PrimeGenerator g2 = new PrimeGenerator(1, 100);
        long size = LongStream.rangeClosed(g2.from, g2.to)
                .filter( (long n)->g2.isPrime(n) )
                .reduce( 0L, (long count, long n)->{
                    System.out.print(n + ", ");
                    return ++count;} );
        System.out.println("\n" + size + " prime numbers are found.");

    }
}
