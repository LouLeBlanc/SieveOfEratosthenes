/**
 * Written by Louis LeBlanc
 * For Homework Assignment 4
 * Release Control and Continuous Integration/Continuous Delivery
 * Brandeis University,
 * Instructed by Eric Hemdal
 * 
 * JUnit 4 tests for the Sieve of Eratosthenes class.
 * 
 */

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import edu.brandeis.rseg126.SOE.SieveOfEratosthenes;

public class SieveOfEratosthenesTest {
    private SieveOfEratosthenes sieve;

    @Before
    public void before() {
      sieve = new SieveOfEratosthenes(100);
    }

    @Test
    public void testIsPrime() {

        assertEquals("Falied out-of-bounds check", -1, sieve.isPrime(200));
        assertEquals("Verifying return for prime check", 1, sieve.isPrime(2));
        assertEquals("Verifying return for non-prime check", 0, sieve.isPrime(10));

    }

    @Test
    public void testGetUpperBound() {

        assertEquals("Returned wrong upper bound", 100, sieve.getUpperBound());

    }

}
