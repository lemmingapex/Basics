package com.lemmingapex.basics.primes;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

public class PrimeGeneratorTest {

	@Test
	public void testGeneration() {
		// number of primes to generate
		long n = 10000l;

		List<Long> Primes = PrimeGenerator.generateNPrimes(n);
		assertEquals(Primes.size(), n);
		assertEquals(Primes.get(10000 - 1).longValue(), 104729l);

		Primes = PrimeGenerator.generatePrimesUpToN(Primes.get(10000 - 1));
		assertEquals(Primes.size(), n);
		assertEquals(Primes.get(10000 - 1).longValue(), 104729l);
	}
}
