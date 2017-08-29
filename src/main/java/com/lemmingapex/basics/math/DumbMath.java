package com.lemmingapex.basics.math;

public final class DumbMath {

	static final double PI = 3.141592653589793;

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

		if ((b % 1) == 0) {
			return intpow(a, (int)b);
		}

		final int iteration_count = 30;

		double temp_pow = b * ln(a);
		boolean negate = (temp_pow < 0);
		if(negate) {
			temp_pow *= -1.0;
		}

		double pow = 1.0;
		double denominator = 1.0;
		double numerator = temp_pow;
		for (int i = 1; i < iteration_count; i++) {
			denominator *= i;
			pow += numerator / ((double) denominator);
			numerator *= temp_pow;
		}

		if(negate) {
			return 1.0/pow;
		} else {
			return pow;
		}
	}

	/**
	 * Return integer power of a^b in O(log(b)) using recursion. a^b =
	 * (a^floor(b/2))*(a^floor(b/2))*(a^b%2)
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	private static double intpow(double a, int b) {
		if (b < 0) {
			return 1.0 / intpowRecurse(a, -b);
		} else {
			return intpowRecurse(a, b);
		}
	}

	public static double intpowRecurse(double a, int b) {
		if (b == 0) {
			return 1;
		}

		double v = intpowRecurse(a, b / 2);

		if (b % 2 == 0) {
			return v * v;
		} else {
			return v * v * a;
		}
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
		boolean gt1 = (sqrt(aminus1 * aminus1) <= 1) ? false : true;

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

	public static double abs(double a) {
		if(a < 0) {
			return -1.0*a;
		}
		return a;
	}

	/**
	 * Babylonian method/Newtons Method
	 *
	 * @param a
	 * @return
	 * @throws IllegalArgumentException
	 */
	public static double sqrt(double a) throws IllegalArgumentException {

		if(a == 0) {
			return 0;
		}

		if (a < 0) {
			throw new IllegalArgumentException("Can not sqrt a negative number!");
		}

		// write  a  as  m * 10^2n  where  1 < m < 100
		// then  sqrt(a)  is around  1.8 * 10^n  or  5.6 * 10^n  depending on  m
		// 1.8 ~ geometric mean of sqrt(1) and sqrt(10). (when m is between 1 and 10)
		// 5.6 ~ geometric mean of sqrt(10) and sqrt(100).  (when m is between 10 and 100)
		// see http://en.wikipedia.org/wiki/Methods_of_computing_square_roots#Rough_estimation for more info

		double mantissa = a;
		double tenToTheN = 1;

		if (a >= 100) {
			while (mantissa / 100 > 1) {
				mantissa /= 100;
				tenToTheN = tenToTheN * 10;
			}
		} else if (a < 1) {
			while (mantissa * 100 <= 1) {
				mantissa *= 100;
				tenToTheN = tenToTheN / 10;
			}
		}

		double roughEstimation;
		if (mantissa < 10) {
			roughEstimation = 1.8 * tenToTheN;
		} else {
			roughEstimation = 5.6 * tenToTheN;
		}

		// newton's method
		// sqrt(a) is same as solving for x in the following:
		// x^2 - a = 0

		// f(x) = x^2 - a
		// f'(x) = 2x
		// see http://en.wikipedia.org/wiki/Methods_of_computing_square_roots for more info

		// loop should break well before max_iteration_count
		final int max_iteration_count = 40;
		double x = roughEstimation;
		for (int i = 1; i < max_iteration_count; i++) {
			double x_n = 0.5 * (x + (a / x));
			if(abs(x_n - x) < Double.MIN_VALUE) {
				x = x_n;
				return x;
			}
			x = x_n;
		}
		return x;
	}

	/**
	 * Uses the taylor series for cos(x)
	 *
	 * @param x in radians
	 * @return
	 */
	public static double cos(double x) {
		x = x%(2*DumbMath.PI);
		final int max_iteration_count = 40;
		double cos_x = 0;
		double sign = -1.0;
		double factorial = 1.0;
		double d = 0.0;
		for (int i = 0; i < max_iteration_count; i++) {
			sign = -1.0*sign;
			double twod = 2*d;
			factorial = twod * (twod - 1.0) * factorial;
			if(factorial <= 0.0) {
				factorial = 1.0;
			}
			double x_n = sign * pow(x, twod) / factorial;
			cos_x += x_n;
			if(abs(x_n) < Double.MIN_VALUE) {
				break;
			}
			d+=1.0;
		}
		return cos_x;
	}

	/**
	 * Uses the taylor series for sin(x)
	 *
	 * @param x in radians
	 * @return
	 */
	public static double sin(double x) {
		x = x%(2*DumbMath.PI);
		final int max_iteration_count = 40;
		double sin_x = 0;
		double sign = -1.0;
		double factorial = 1.0;
		double d = 0.0;
		for (int i = 0; i < max_iteration_count; i++) {
			sign = -1.0*sign;
			double twod = 2*d;
			factorial = twod * (twod + 1.0) * factorial;
			if(factorial <= 0.0) {
				factorial = 1.0;
			}
			double x_n = sign * pow(x, twod + 1.0) / factorial;
			sin_x += x_n;
			if(abs(x_n) < Double.MIN_VALUE) {
				break;
			}
			d+=1.0;
		}
		return sin_x;
	}

	public static double tan(double x) {
		return sin(x)/cos(x);
	}
}
