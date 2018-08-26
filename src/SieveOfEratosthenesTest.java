import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import edu.brandeis.rseg126.SOE.SieveOfEratosthenes;

class SieveOfEratosthenesTest {

    @Test
    void testIsPrime() {
        SieveOfEratosthenes sieve = new SieveOfEratosthenes(100);

        System.out.println("Verifying return for out-of-bounds check");
        assertEquals(-1, sieve.isPrime(200));
        System.out.println("Verifying return for prime check");
        assertEquals(1, sieve.isPrime(2));
        System.out.println("Verifying return for non-prime check");
        assertEquals(0, sieve.isPrime(10));
    }

    @Test
    void testGetUpperBound() {
        SieveOfEratosthenes sieve = new SieveOfEratosthenes(100);

        System.out.println("Verifying that a custom upper bound can be retrieved");
        System.out.println("Expect 100; Got: " + sieve.getUpperBound());
        assertEquals(100, sieve.getUpperBound());
    }

}
