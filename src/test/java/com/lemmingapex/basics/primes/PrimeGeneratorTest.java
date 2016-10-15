package com.lemmingapex.basics.primes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PrimeGeneratorTest {

	@Test
	public void testGeneration() {
		// number of primes to generate
		long n = 10000L;

		List<Long> primes = PrimeGenerator.generateNPrimes(n);
		assertEquals(primes.size(), n);
		assertEquals(primes.get(((int)n) - 1).longValue(), 104729L);

		primes = PrimeGenerator.generatePrimesUpToN(primes.get(((int)n) - 1));
		assertEquals(primes.size(), n);
		assertEquals(primes.get(((int)n) - 1).longValue(), 104729L);

		n = 4000000l;
		primes = PrimeGenerator.generateNPrimes(n);
		assertEquals(primes.size(), n);
		assertEquals(primes.get(((int)n) - 1).longValue(), 67867967L);

		primes = PrimeGenerator.generatePrimesUpToN(primes.get(((int)n) - 1));
		assertEquals(primes.size(), n);
		assertEquals(primes.get(((int)n) - 1).longValue(), 67867967L);
	}
}
