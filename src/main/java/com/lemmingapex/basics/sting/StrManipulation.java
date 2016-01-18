package com.lemmingapex.basics.sting;

public class StrManipulation {

	public static void combinations(String prefix, String suffix) {
		if (suffix.length() < 0) {
			return;
		}
		System.out.println(prefix);
		for (int i = 0; i < suffix.length(); i++) {
			combinations(prefix + suffix.charAt(i), suffix.substring(i + 1));
		}
	}
}