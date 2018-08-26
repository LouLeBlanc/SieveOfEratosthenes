package edu.brandeis.rseg126.SOE;
/**
 * Written by Louis LeBlanc
 * For Homework Assignment 4
 * Release Control and Continuous Integration/Continuous Delivery
 * Brandeis University,
 * Instructed by Eric Hemdal
 * 
 * Java implementation of the Sieve of Eratosthenes,
 * an algorithm to find all prime numbers up to a specified limit.
 * 
 * Implementation based on details retrieved from GeeksforGeeks website:
 * https://www.geeksforgeeks.org/sieve-of-eratosthenes/
 * 
 * Java command to run:
 * java -cp  dist/lib/SieveOfEratosthenes-${DSTAMP}.jar edu.brandeis.rseg126.SOE.SieveOfEratosthenes
 * 
 */

import java.util.Scanner;

/**
 * @author Louis LeBlanc The Sieve of Eratosthenes is an algorithm that can
 *         quickly find all of the lower prime numbers up to a specified upper
 *         bound.
 *
 */

public class SieveOfEratosthenes {

    private static boolean[] isPrime;
    private int       upperBound    = 300;
    private static boolean   isInitialized = false;
    private static boolean   scanComplete  = false;

    /**
     * Create a Sieve object to filter integers up to n.
     * 
     * @param n Define the upper bound of integers to filter for prime numbers.
     */
    public SieveOfEratosthenes(int n) {
        initPrimes(n);
        sieveOfEratosthenes();
    }

     /**
     * Display all primes filtered by the Sieve object.
     * Display is formatted in 8 columns per line.
     */
    public void printPrimes() {
        if (!scanComplete)
            sieveOfEratosthenes(); // Call with defaults ...
        // print prime numbers
        int cols = 0; // We'll print our primes out in 8 columns
        for (int i = 2; i <= this.upperBound; i++) {
            if (isPrime[i]) {
                System.out.printf("%8d", i);
                cols++;
            }
            if (cols >= 8) {
                cols = 0;
                System.out.println("");
            }
        }
        System.out.println("");
    }

    /**
     * Checks an integer against the primes filtered by the Sieve.
     * 
     * @param n check if n is prime
     * @return  -1, 0, 1, depending on whether the integer is known to be prime.
     *<p>      -1: Unknown.  n is > defined limit checked by this object.
     *<p>       0: Not prime.
     *<p>       1: Prime.
     */
    public int isPrime(int n) {
        if (n > upperBound)
            return -1;
        else if (isPrime[n])
            return 1;
        else
            return 0;
    }

    public int getUpperBound() {
        return this.upperBound;
    }

    /* Assumes initPrimes() has been called. */
    private void sieveOfEratosthenes() {
        if (!isInitialized)
            initPrimes(); // Use defaults
        for (int p = 2; p * p <= upperBound; p++) {
            if (isPrime[p] == true) {
                for (int i = p * 2; i <= upperBound; i += p)
                    isPrime[i] = false;
            }
        }
        scanComplete = true;
    }

    // Initialize with the default (300)
    private void initPrimes() {
        initPrimes(300);
    }

    // Specify the upper bound to check for primes
    private void initPrimes(int n) {
        upperBound = n;
        isPrime = new boolean[upperBound + 1];

        for (int i = 0; i <= upperBound; i++) {
            isPrime[i] = true;
        }
        isInitialized = true;
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        int n = 300;
        SieveOfEratosthenes sieve;
        Scanner s = new Scanner(System.in);
        String inputStr = "";

        // Take input as a string. We'll then scan it for numerical input
        System.out.print("Provide an upper bound for prime numbers [default: 300]: ");
        inputStr = s.nextLine(); // allows us to accept empty input
        s.close();

        // Check if our line included a number
        if (!inputStr.isEmpty()) {
            s = new Scanner(inputStr);
            if (s.hasNextInt())
                n = s.nextInt();
            s.close();
        }

        System.out.println("\nPrinting all primes between 2 and " + n);

        sieve = new SieveOfEratosthenes(n);
        sieve.printPrimes();
    }


}
