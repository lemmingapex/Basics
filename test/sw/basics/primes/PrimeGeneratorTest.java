package sw.basics.primes;

public class PrimeGeneratorTest {
	public static void main(String args[]) {
		long n = 104729l;
		Long[] mySet = PrimeGenerator.generatePrimesUpToN(n).toArray(new Long[0]);
		for (int i = 0; i < mySet.length; i++) {
			System.out.println(mySet[i]);
		}	
	}
}
