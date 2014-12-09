package sw.basics.math;

import org.junit.Test;

public class DumbMathTest {

	@Test
	public void testln() {
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
		System.out.println("java.lang: " + Math.pow(2.0, 3.0));
		System.out.println("dumb.math: " + DumbMath.pow(2.0, 3.0));

		System.out.println("java.lang: " + Math.pow(2.0, 3.1));
		System.out.println("dumb.math: " + DumbMath.pow(2.0, 3.1));

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

}
