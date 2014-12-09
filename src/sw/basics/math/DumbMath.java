package sw.basics.math;

public final class DumbMath {
	
	/**
	 * 
	 * Calculates a^b using only sums and products.
	 * 
	 * Consider the following:
	 * 
	 * x = a^b
	 * 
	 * x = e^ln(a^b)
	 * 
	 * x = e^(b*ln(a))
	 * 
	 * 
	 * The exponential function and natural log can both be expressed as taylor
	 * series that only contains sums and products (or integer Exponentiation, which is a cumulative product).
	 * 
	 * e^x =  sum {n=0 to infinity} (x^n)/n!
	 * 
	 * Precision is determined by a few factors. First, to what bound are the
	 * taylor expansions expressed to? 30 maybe? Experimental testing will help
	 * determine this value. Second, the algorithm will be subject to the
	 * numerical limits of doubles. I expect that a very small a in combination
	 * with a large b will give poor results.
	 * 
	 * @param a, base
	 * @param b, exponent
	 * @return a^b
	 */
	public static double pow(double a, double b) {
		double pow = 1.0;

		final int iteration_count = 30;

		if ((b - Math.floor(b)) == 0) {
			pow = a;
			for (int i = 1; i < b; i++) {
				pow *= a;
			}
			return pow;
		}

		double temp_pow = b * ln(a);

		double denominator = 1.0;
		double numerator = temp_pow;
		for (int i = 1; i < iteration_count; i++) {
			denominator *= i;
			pow += numerator / ((double) denominator);
			numerator *= temp_pow;
		}

		return pow;
	}
	
	
	/**
	 * 
	 * ln(x) = sum {n=1 to infinity} (((-1)^(n+1))/n)*((x-1)^n) if |x-1|<=1
	 * 
	 * ln(x) = sum {n=1 to infinity} 1/(n/(n-1))^n if |x|>1
	 * 
	 * @param a
	 * @return
	 */
	public static double ln(double a) {

		double aminus1 = a - 1.0;
		boolean gt1 = (Math.sqrt(aminus1 * aminus1) <= 1) ? false : true;

		final int iteration_count = 300;

		double lnsum = 0.0;

		if (gt1) {
			// use trick for fast convergence:
			// ln(123.456) = ln(1.23456*10^2)
			// ln(123.456) = ln(1.23456) + 2*ln(10)
			int j = 0;
			while (a / 10 > 1) {
				a /= 10;
				j++;
			}
			aminus1 = a - 1.0;

			double denominator = 1.0;
			for (int i = 1; i < iteration_count; i++) {
				denominator *= a / aminus1;
				lnsum += (1.0 / (denominator * ((double) i)));
			}
			if (j > 0) {
				lnsum += j * ln(10);
			}
		} else {

			int j = 0;
			while (a * 10 < 1) {
				a *= 10;
				j++;
			}
			aminus1 = a - 1.0;

			double numerator = 1.0;
			for (int i = 1; i < iteration_count; i++) {
				double sign = (i % 2 == 0) ? -1.0 : 1.0;
				numerator *= aminus1;
				lnsum += sign * (numerator / ((double) i));
			}

			if (j > 0) {
				lnsum += -1.0 * j * ln(10);
			}
		}

		return lnsum;
	}

	// TODO
	public static double sqrt(double a) {
		return -1.0;
	}

}