package com.lemmingapex.basics.math;

import org.junit.Test;

public class DumbMathTest {

	@Test
	public void testln() {
		System.out.println("testln");
		System.out.println("java.lang: " + Math.log(0.0000003));
		System.out.println("dumb.math: " + DumbMath.ln(0.0000003));

		System.out.println("java.lang: " + Math.log(90000001.3));
		System.out.println("dumb.math: " + DumbMath.ln(90000001.3));

		System.out.println("java.lang: " + Math.log(1));
		System.out.println("dumb.math: " + DumbMath.ln(1));

		System.out.println("java.lang: " + Math.log(.1));
		System.out.println("dumb.math: " + DumbMath.ln(.1));

		System.out.println("java.lang: " + Math.log(10.1));
		System.out.println("dumb.math: " + DumbMath.ln(10.1));
	}

	@Test
	public void testpow() {
		System.out.println("testpow");
		System.out.println("java.lang: " + Math.pow(2.0, 3.0));
		System.out.println("dumb.math: " + DumbMath.pow(2.0, 3.0));

		System.out.println("java.lang: " + Math.pow(2.0, -3.0));
		System.out.println("dumb.math: " + DumbMath.pow(2.0, -3.0));
		
		System.out.println("java.lang: " + Math.pow(3.0, 23.0));
		System.out.println("dumb.math: " + DumbMath.pow(3.0, 23.0));

		System.out.println("java.lang: " + Math.pow(3.0, -23.0));
		System.out.println("dumb.math: " + DumbMath.pow(3.0, -23.0));
		
		System.out.println("java.lang: " + Math.pow(2.0, 3.1));
		System.out.println("dumb.math: " + DumbMath.pow(2.0, 3.1));

		System.out.println("java.lang: " + Math.pow(0.25, -0.25));
		System.out.println("dumb.math: " + DumbMath.pow(0.25, -0.25));

		System.out.println("java.lang: " + Math.pow(12.2, -8.16));
		System.out.println("dumb.math: " + DumbMath.pow(12.2, -8.16));
		
		System.out.println("java.lang: " + Math.pow(7.0, 4.2));
		System.out.println("dumb.math: " + DumbMath.pow(7.0, 4.2));

		System.out.println("java.lang: " + Math.pow(16.0, 8.1));
		System.out.println("dumb.math: " + DumbMath.pow(16.0, 8.1));

		System.out.println("java.lang: " + Math.pow(2.0, 5.1));
		System.out.println("dumb.math: " + DumbMath.pow(2.0, 5.1));

		System.out.println("java.lang: " + Math.pow(0.2, 5.1));
		System.out.println("dumb.math: " + DumbMath.pow(0.2, 5.1));

		System.out.println("java.lang: " + Math.pow(0.02, 5.1));
		System.out.println("dumb.math: " + DumbMath.pow(0.02, 5.1));
	}

	
	@Test
	public void testsqrt() {
		System.out.println("testsqrt");
		System.out.println("java.lang: " + Math.sqrt(9.0));
		System.out.println("dumb.math: " + DumbMath.sqrt(9.0));

		System.out.println("java.lang: " + Math.sqrt(90.0));
		System.out.println("dumb.math: " + DumbMath.sqrt(90.0));

		System.out.println("java.lang: " + Math.sqrt(9000.0));
		System.out.println("dumb.math: " + DumbMath.sqrt(9000.0));

		System.out.println("java.lang: " + Math.sqrt(0));
		System.out.println("dumb.math: " + DumbMath.sqrt(0));

		System.out.println("java.lang: " + Math.sqrt(0.1));
		System.out.println("dumb.math: " + DumbMath.sqrt(0.1));

		System.out.println("java.lang: " + Math.sqrt(125348));
		System.out.println("dumb.math: " + DumbMath.sqrt(125348));

		System.out.println("java.lang: " + Math.sqrt(0.00001));
		System.out.println("dumb.math: " + DumbMath.sqrt(0.00001));
		
		System.out.println("java.lang: " + Math.sqrt(Double.MAX_VALUE));
		System.out.println("dumb.math: " + DumbMath.sqrt(Double.MAX_VALUE));
		
		System.out.println("java.lang: " + Math.sqrt(Double.MIN_VALUE));
		System.out.println("dumb.math: " + DumbMath.sqrt(Double.MIN_VALUE));
	}
}
