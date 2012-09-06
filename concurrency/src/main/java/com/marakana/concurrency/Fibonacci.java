package com.marakana.concurrency;

import java.math.BigInteger;
import java.util.Random;

public class Fibonacci {

	private static final Random RANDOM = new Random();
	private static final int MAX_FIBONACCI = 40;

	public static BigInteger fibonacci(int n) {
		return n < 2
			? BigInteger.ONE
			: fibonacci(n - 1).add(fibonacci(n - 2));
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			Thread t = new Thread() {
				@Override
				public void run() {
					System.out.println(fibonacci(RANDOM.nextInt(MAX_FIBONACCI)));
				}
			};
			t.start();
		}
	}
}
