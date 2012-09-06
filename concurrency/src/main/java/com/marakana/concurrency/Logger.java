package com.marakana.concurrency;

import java.util.concurrent.atomic.AtomicReference;

import com.marakana.immutablelist.ImmutableStack;

public class Logger implements Runnable {

	private final AtomicReference<ImmutableStack<Object>> queue =
		new AtomicReference<ImmutableStack<Object>>(ImmutableStack.empty());

	public void log(Object o) {
		ImmutableStack<Object> before, after;
		do {
			before = queue.get();
			after = before.push(o);
		} while (!queue.compareAndSet(before, after));
	}

	@Override
	public void run() {
		while (true) {
			ImmutableStack<Object> before = queue.get();
			if (before.isEmpty()) {
				Thread.yield();
			} else {
				ImmutableStack<Object> after = before.tail();
				if (queue.compareAndSet(before, after)) {
					System.out.println(before.head());
				}
			}
		}
	}
}
