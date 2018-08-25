/**
 * Written by Louis LeBlanc
 * For Homework Assignment 4
 * Release Control and Continuous Integration/Continuous Delivery
 * Brandeis University,
 * Instructed by Eric Hemdal
 * 
 * JUnit tests for the SieveOfEratosthenes class.
 * 
 */


package edu.brandeis.rseg126.SOE;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class SieveOfEratosthenesTest {

    @Test
    public void testSieveOfEratosthenes() {
        SieveOfEratosthenes sieve = new SieveOfEratosthenes(100);

        // Test the upperBound default.
        System.out.println("Verifying that a custom upper bound can be retrieved");
        System.out.println("Expect 100; Got: " + sieve.getUpperBound());
        assertEquals(100, sieve.getUpperBound());
    }

    @Test
    public void testGetUpperBound() {
        SieveOfEratosthenes sieve = new SieveOfEratosthenes(100);
        System.out.println("Verifying return for out-of-bounds check");
        assertEquals(-1, sieve.isPrime(200));
        System.out.println("Verifying return for prime check");
        assertEquals(1, sieve.isPrime(2));
        System.out.println("Verifying return for non-prime check");
        assertEquals(0, sieve.isPrime(10));
    }

}
