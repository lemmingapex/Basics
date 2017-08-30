package com.lemmingapex.basics.math;

public final class DumbMath {

	static final double PI = 3.141592653589793;
	static final double E =  2.718281828459045;

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
	 * The exponential function and natural log can both be expressed as maclaurin
	 * series that only contains sums and products (or integer Exponentiation, which is a cumulative product).
	 *
	 * maclaurin series:
	 * e^x = sum {n=0 to infinity} (x^n)/n!
	 *
	 * Precision is determined by a few factors. First, to what bound are the
	 * taylor expansions expressed to? 50 maybe?
	 *
	 * @param a, base
	 * @param b, exponent
	 * @return a^b
	 */
	public static double pow(double a, double b) {

		if ((b % 1) == 0) {
			return intpow(a, (long)b);
		}

		final int iteration_count = 50;

		double full_pow = b * ln(a);
		boolean negate = (full_pow < 0);
		if(negate) {
			full_pow *= -1.0;
		}
		double e_pow = full_pow%1;

		double pow = 1.0;
		double denominator = 1.0;
		double numerator = e_pow;
		for (int i = 1; i < iteration_count; i++) {
			denominator *= i;
			pow += numerator / ((double) denominator);
			numerator *= e_pow;
		}

		pow = pow*intpow(DumbMath.E, (long)floor(full_pow));
		if(negate) {
			pow = 1.0/pow;
		}
		return pow;
	}

	/**
	 * Return integer power of a^b in O(log(b)) using recursion. a^b =
	 * (a^floor(b/2))*(a^floor(b/2))*(a^b%2)
	 *
	 * @param a
	 * @param b
	 * @return
	 */
	private static double intpow(double a, long b) {
		if (b < 0) {
			return 1.0 / intpowRecurse(a, -b);
		} else {
			return intpowRecurse(a, b);
		}
	}

	public static double intpowRecurse(double a, long b) {
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
	 * maclaurin series:
	 * ln(x) = sum {i=1 to infinity} (((-1)^(i+1))/i)*((x-1)^i) if |x-1|<1 (aka x<2)
	 *
	 * @param a
	 * @return
	 */
	public static double ln(double a) {
		final int iteration_count = 300;

		double lnsum = 0.0;

		// use trick for fast convergence:
		// ln(123.456) = ln(.123456*10^3)
		// ln(123.456) = ln(.123456) + 3*ln(10)
		// ln(123.456) = ln(.123456) - 3*ln(1/10)
		int j = 0;
		while (a >= 2.0) {
			a /= 10;
			j++;
		}
		while (a < 0.1) {
			a *= 10;
			j--;
		}

		double aminus1 = a - 1.0;
		double numerator = 1.0;
		for (int i = 1; i < iteration_count; i++) {
			double sign = (i % 2 == 0) ? -1.0 : 1.0;
			numerator *= aminus1;
			lnsum += sign * (numerator / ((double) i));
		}
		if(j != 0) {
			lnsum -= j * ln(0.1);
		}

		return lnsum;
	}

	public static double abs(double a) {
		if(a < 0) {
			return -a;
		}
		return a;
	}

	public static double floor(double a) {
		if ((long) a == a) {
			return a;
		}
		double x = abs(a);
		if (x < 1) {
			return a >= 0 ? 0 * a : -1;
		}
		return a < 0 ? (long) a - 1.0 : (long) a;
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
