package com.marakana.concurrency;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Fibonacci {

	private static final Random RANDOM = new Random();
	private static final int MAX_FIBONACCI = 40;

	public static BigInteger fibonacci(int n) {
		return n < 2
			? BigInteger.ONE
			: fibonacci(n - 1).add(fibonacci(n - 2));
	}

	public static void main(String[] args) throws Exception {
		ExecutorService executor = Executors.newFixedThreadPool(10);

		List<Callable<BigInteger>> tasks = new ArrayList<Callable<BigInteger>>();

		for (int i = 0; i < 20; i++) {
			tasks.add(new Callable<BigInteger>() {

				@Override
				public BigInteger call() throws Exception {
					return fibonacci(RANDOM.nextInt(MAX_FIBONACCI));
				}
				
			});
		}

		List<Future<BigInteger>> futures = executor.invokeAll(tasks);

		for (Future<BigInteger> future : futures) {
			System.out.println(future.get());
		}

		executor.shutdown();
	}
}
